extends Node3D

# NsaKasa 3D Custom Avatar GDScript Controller
# Listens for gesture signals from Kotlin GodotBridgePlugin and animates res://avatar.glb

signal gesture_received(gesture_name)

@onready var avatar_model: Node3D = $AvatarModelInstance
@onready var status_label: Label3D = $StatusLabel

var current_gesture: String = "IDLE"

func _ready():
	print("NsaKasa 3D Custom Avatar (avatar.glb) Godot scene initialized.")
	if status_label:
		status_label.text = "Avatar Model Loaded (avatar.glb)"
	if Engine.has_singleton("GodotBridgePlugin"):
		var bridge = Engine.get_singleton("GodotBridgePlugin")
		bridge.connect("on_gesture_detected", Callable(this, "_on_gesture_detected"))
		print("Connected to Kotlin GodotBridgePlugin singleton!")

func _on_gesture_detected(gesture_name: String):
	current_gesture = gesture_name
	if status_label:
		status_label.text = "Avatar Sign: " + gesture_name
	print("Custom Avatar received gesture: ", gesture_name)
	
	if avatar_model:
		# Animate 3D Avatar according to recognized Ghanaian Sign Language gesture
		match gesture_name:
			"OPEN_PALM":
				avatar_model.rotation_degrees = Vector3(0, 0, 0)
			"CLOSED_FIST":
				avatar_model.rotation_degrees = Vector3(0, 30, 0)
			"POINTING":
				avatar_model.rotation_degrees = Vector3(0, -30, 0)
			"THUMBS_UP":
				avatar_model.rotation_degrees = Vector3(-10, 0, 0)
			_:
				avatar_model.rotation_degrees = Vector3.ZERO
