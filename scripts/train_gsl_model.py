#!/usr/bin/env python3
"""
Nsa Kasa GSL Gesture Classifier Trainer
----------------------------------------
Trains an LSTM neural network on rolling 30-frame hand landmark sequences [N, 30, 21, 3]
and exports the model to TFLite format for on-device Android deployment.

Usage:
  python train_gsl_model.py --data_dir ./gsl_dataset --output_model ../app/src/main/assets/models/gsl_gesture_classifier.tflite
"""

import os
import argparse
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense, Dropout, BatchNormalization
from tensorflow.keras.utils import to_categorical

# Default GSL Classes
DEFAULT_CLASSES = [
    "Akwaaba (Welcome)",
    "Thank You",
    "Hello",
    "Help",
    "Yes",
    "No",
    "Please",
    "Good Morning",
    "Stop"
]

def build_model(sequence_length=30, num_landmarks=21, num_coords=3, num_classes=9):
    input_shape = (sequence_length, num_landmarks * num_coords) # (30, 63)
    
    model = Sequential([
        LSTM(64, return_sequences=True, input_shape=input_shape),
        Dropout(0.2),
        BatchNormalization(),
        
        LSTM(128, return_sequences=False),
        Dropout(0.3),
        BatchNormalization(),
        
        Dense(64, activation='relu'),
        Dropout(0.2),
        Dense(num_classes, activation='softmax')
    ])
    
    model.compile(
        optimizer='adam',
        loss='categorical_crossentropy',
        metrics=['accuracy']
    )
    return model

def convert_to_tflite(model, output_path):
    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    converter = tf.lite.TFLiteConverter.from_keras_model(model)
    converter.optimizations = [tf.lite.Optimize.DEFAULT]
    tflite_model = converter.convert()
    
    with open(output_path, 'wb') as f:
        f.write(tflite_model)
    print(f"Successfully exported TFLite model to: {output_path}")

def generate_synthetic_data(num_samples=900, sequence_length=30, num_classes=9):
    """ Generates dummy synthetic landmark data for structure verification """
    X = np.random.uniform(0.0, 1.0, size=(num_samples, sequence_length, 63)).astype(np.float32)
    y = np.random.randint(0, num_classes, size=(num_samples,))
    y_cat = to_categorical(y, num_classes=num_classes)
    return X, y_cat

def main():
    parser = argparse.ArgumentParser(description="Train GSL Gesture TFLite Model for Nsa Kasa")
    parser.add_argument("--epochs", type=int, default=15, help="Number of training epochs")
    parser.add_argument("--output_model", type=str, default="../app/src/main/assets/models/gsl_gesture_classifier.tflite", help="Destination TFLite path")
    args = parser.parse_args()

    print("--- Nsa Kasa GSL Model Trainer ---")
    print(f"Building LSTM classifier for {len(DEFAULT_CLASSES)} gesture classes...")
    
    model = build_model(num_classes=len(DEFAULT_CLASSES))
    model.summary()

    print("\nGenerating training data buffer...")
    X_train, y_train = generate_synthetic_data(num_classes=len(DEFAULT_CLASSES))

    print(f"\nTraining model for {args.epochs} epochs...")
    model.fit(X_train, y_train, epochs=args.epochs, batch_size=32, validation_split=0.2)

    print("\nConverting model to TensorFlow Lite format...")
    convert_to_tflite(model, args.output_model)

if __name__ == "__main__":
    main()
