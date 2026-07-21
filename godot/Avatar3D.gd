extends Node3D

# NsaKasa 3D Avatar GDScript Controller
# Listens for gesture signals from Kotlin GodotBridgePlugin

signal gesture_received(gesture_name)

@onready var avatar_mesh: MeshInstance3D = $AvatarMesh
@onready var status_label: Label3D = $StatusLabel

var current_gesture: String = "IDLE"

func _ready():
	print("NsaKasa 3D Avatar Godot scene initialized.")
	if Engine.has_singleton("GodotBridgePlugin"):
		var bridge = Engine.get_singleton("GodotBridgePlugin")
		bridge.connect("on_gesture_detected", Callable(this, "_on_gesture_detected"))
		print("Connected to Kotlin GodotBridgePlugin singleton!")

func _on_gesture_detected(gesture_name: String):
	current_gesture = gesture_name
	if status_label:
		status_label.text = "Gesture: " + gesture_name
	print("Avatar received gesture: ", gesture_name)
	
	# Animate avatar mesh according to detected sign language gesture
	match gesture_name:
		"OPEN_PALM":
			avatar_mesh.rotation_degrees.y = 0
		"CLOSED_FIST":
			avatar_mesh.rotation_degrees.y = 45
		"POINTING":
			avatar_mesh.rotation_degrees.y = -45
		"THUMBS_UP":
			avatar_mesh.rotation_degrees.x = -15
		_:
			avatar_mesh.rotation_degrees = Vector3.ZERO
