extends Node3D

# NsaKasa 3D Interactive Avatar GDScript Controller
# Features:
# 1. Idle Breathing & Delayed Friendly Waving
# 2. Smooth Camera Zoom to Avatar Hands during GSL Learning
# 3. Interactive GSL Teaching Demonstrations (Alphabet A-Z, Words, Sentences)

signal gesture_received(gesture_name)

@onready var avatar_model: Node3D = $AvatarModelInstance
@onready var status_label: Label3D = $StatusLabel
@onready var camera: Camera3D = $"../Camera3D"

var current_gesture: String = "IDLE"
var is_zoomed_in: Boolean = false

# Default Camera Positions
var full_body_cam_pos: Vector3 = Vector3(0, 1.2, 2.8)
var hand_zoom_cam_pos: Vector3 = Vector3(0, 1.25, 1.1)

# Animation Timers
var wave_timer: float = 0.0
var has_waved: bool = false
var breathing_time: float = 0.0

func _ready():
	print("NsaKasa 3D Interactive Avatar (breathing + waving + hand zoom) initialized.")
	if status_label:
		status_label.text = "Hello! Breathing Idle..."
	
	if Engine.has_singleton("GodotBridgePlugin"):
		var bridge = Engine.get_singleton("GodotBridgePlugin")
		bridge.connect("on_gesture_detected", Callable(this, "_on_gesture_detected"))
		print("Connected to Kotlin GodotBridgePlugin singleton!")

func _process(delta: float):
	# 1. Gentle Idle Breathing Animation
	breathing_time += delta * 2.0
	if avatar_model:
		var breath_offset = sin(breathing_time) * 0.015
		avatar_model.position.y = breath_offset
		avatar_model.scale.y = 1.0 + (sin(breathing_time) * 0.008)

	# 2. Automatic Friendly Wave after 2.5 seconds
	if not has_waved:
		wave_timer += delta
		if wave_timer >= 2.5:
			perform_waving_greeting()
			has_waved = true

func perform_waving_greeting():
	if status_label:
		status_label.text = "👋 Welcome to NsaKasa GSL Avatar!"
	print("Avatar performing friendly greeting wave!")
	
	if avatar_model:
		var tween = create_tween()
		tween.tween_property(avatar_model, "rotation_degrees:z", 12.0, 0.3)
		tween.tween_property(avatar_model, "rotation_degrees:z", -12.0, 0.3)
		tween.tween_property(avatar_model, "rotation_degrees:z", 12.0, 0.3)
		tween.tween_property(avatar_model, "rotation_degrees:z", 0.0, 0.3)

func zoom_to_hands(enable_zoom: bool):
	is_zoomed_in = enable_zoom
	if camera:
		var target_pos = hand_zoom_cam_pos if is_zoomed_in else full_body_cam_pos
		var tween = create_tween()
		tween.set_ease(Tween.EASE_OUT)
		tween.set_trans(Tween.TRANS_CUBIC)
		tween.tween_property(camera, "position", target_pos, 0.8)

	if status_label:
		status_label.text = "🔍 Zoomed to Hands: GSL Mode" if is_zoomed_in else "👤 Full Body Avatar View"

func _on_gesture_detected(gesture_name: String):
	current_gesture = gesture_name
	print("Avatar teaching GSL sign: ", gesture_name)
	
	# Automatically zoom in to hands when learning signs
	zoom_to_hands(true)

	if status_label:
		status_label.text = "Teaching GSL: " + gesture_name
	
	if avatar_model:
		var tween = create_tween()
		match gesture_name:
			"LETTER_A", "OPEN_PALM":
				tween.tween_property(avatar_model, "rotation_degrees:y", 25.0, 0.4)
			"LETTER_B", "CLOSED_FIST":
				tween.tween_property(avatar_model, "rotation_degrees:y", -25.0, 0.4)
			"LETTER_C", "POINTING":
				tween.tween_property(avatar_model, "rotation_degrees:x", -15.0, 0.4)
			"THUMBS_UP", "THANK_YOU":
				tween.tween_property(avatar_model, "rotation_degrees:x", 15.0, 0.4)
			_:
				tween.tween_property(avatar_model, "rotation_degrees", Vector3.ZERO, 0.4)
