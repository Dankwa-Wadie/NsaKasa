package com.nsakasa.features.learn.model

import com.nsakasa.core.camera.LandmarkPoint

/**
 * Represents a single GSL / Sign Language item in the study and test catalog.
 */
data class GslSignItem(
    val id: String,
    val label: String,
    val category: String, // "Alphabet (A-Z)", "Greetings", "Essentials"
    val twiTranslation: String,
    val description: String,
    val landmarks3D: List<LandmarkPoint>,
    val practiceTip: String,
    val difficulty: String = "Beginner"
)

object GslDataset {

    // Helper to generate realistic 3D landmarks for hand shapes
    private fun createPose(
        thumbCurl: Float = 0.5f,
        indexCurl: Float = 0.0f,
        middleCurl: Float = 0.0f,
        ringCurl: Float = 0.0f,
        pinkyCurl: Float = 0.0f,
        thumbSpread: Float = 0.2f
    ): List<LandmarkPoint> {
        val points = mutableListOf<LandmarkPoint>()
        
        // 0: Wrist
        points.add(LandmarkPoint(0.5f, 0.85f, 0.0f))

        // Thumb: 1, 2, 3, 4
        points.add(LandmarkPoint(0.42f - thumbSpread * 0.1f, 0.75f, 0.02f))
        points.add(LandmarkPoint(0.35f - thumbSpread * 0.2f, 0.65f + thumbCurl * 0.1f, 0.05f))
        points.add(LandmarkPoint(0.30f - thumbSpread * 0.25f, 0.58f + thumbCurl * 0.15f, 0.08f))
        points.add(LandmarkPoint(0.26f - thumbSpread * 0.3f, 0.52f + thumbCurl * 0.2f, 0.1f))

        // Index: 5, 6, 7, 8
        points.add(LandmarkPoint(0.42f, 0.55f, 0.02f))
        points.add(LandmarkPoint(0.40f, 0.45f + indexCurl * 0.15f, -indexCurl * 0.05f))
        points.add(LandmarkPoint(0.39f, 0.35f + indexCurl * 0.25f, -indexCurl * 0.1f))
        points.add(LandmarkPoint(0.38f, 0.25f + indexCurl * 0.35f, -indexCurl * 0.12f))

        // Middle: 9, 10, 11, 12
        points.add(LandmarkPoint(0.50f, 0.54f, 0.02f))
        points.add(LandmarkPoint(0.50f, 0.43f + middleCurl * 0.15f, -middleCurl * 0.05f))
        points.add(LandmarkPoint(0.50f, 0.32f + middleCurl * 0.25f, -middleCurl * 0.1f))
        points.add(LandmarkPoint(0.50f, 0.22f + middleCurl * 0.35f, -middleCurl * 0.12f))

        // Ring: 13, 14, 15, 16
        points.add(LandmarkPoint(0.58f, 0.56f, 0.02f))
        points.add(LandmarkPoint(0.59f, 0.46f + ringCurl * 0.15f, -ringCurl * 0.05f))
        points.add(LandmarkPoint(0.60f, 0.36f + ringCurl * 0.25f, -ringCurl * 0.1f))
        points.add(LandmarkPoint(0.61f, 0.27f + ringCurl * 0.35f, -ringCurl * 0.12f))

        // Pinky: 17, 18, 19, 20
        points.add(LandmarkPoint(0.65f, 0.58f, 0.02f))
        points.add(LandmarkPoint(0.67f, 0.50f + pinkyCurl * 0.15f, -pinkyCurl * 0.05f))
        points.add(LandmarkPoint(0.69f, 0.42f + pinkyCurl * 0.25f, -pinkyCurl * 0.1f))
        points.add(LandmarkPoint(0.71f, 0.35f + pinkyCurl * 0.35f, -pinkyCurl * 0.12f))

        return points
    }

    val alphabetSigns: List<GslSignItem> = listOf(
        GslSignItem(
            id = "alpha_a",
            label = "Letter A",
            category = "Alphabet (A-Z)",
            twiTranslation = "A",
            description = "Make a fist with fingers curled tightly into palm. Extend thumb straight up resting against the side of the index finger.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.8f),
            practiceTip = "Keep thumb upright along the side of index knuckle."
        ),
        GslSignItem(
            id = "alpha_b",
            label = "Letter B",
            category = "Alphabet (A-Z)",
            twiTranslation = "B",
            description = "Open palm with all four fingers held straight together pointing up. Curl thumb across the front of the palm.",
            landmarks3D = createPose(thumbCurl = 1.0f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.0f),
            practiceTip = "Fingers straight together; thumb tucked flat against palm."
        ),
        GslSignItem(
            id = "alpha_c",
            label = "Letter C",
            category = "Alphabet (A-Z)",
            twiTranslation = "C",
            description = "Curve fingers and thumb into a C shape, like holding a small cup or tube sideways.",
            landmarks3D = createPose(thumbCurl = 0.4f, indexCurl = 0.4f, middleCurl = 0.4f, ringCurl = 0.4f, pinkyCurl = 0.4f, thumbSpread = 0.5f),
            practiceTip = "Keep fingers smoothly curved into a clear half-circle shape."
        ),
        GslSignItem(
            id = "alpha_d",
            label = "Letter D",
            category = "Alphabet (A-Z)",
            twiTranslation = "D",
            description = "Point index finger straight up. Touch thumb tip to tips of middle, ring, and pinky fingers to form an O shape at base.",
            landmarks3D = createPose(thumbCurl = 0.7f, indexCurl = 0.0f, middleCurl = 0.8f, ringCurl = 0.8f, pinkyCurl = 0.8f, thumbSpread = 0.4f),
            practiceTip = "Index finger erect; other fingers form a ring with thumb."
        ),
        GslSignItem(
            id = "alpha_e",
            label = "Letter E",
            category = "Alphabet (A-Z)",
            twiTranslation = "E",
            description = "Curl all four fingers downward so fingertip pads rest on thumb curled across palm.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 0.9f, middleCurl = 0.9f, ringCurl = 0.9f, pinkyCurl = 0.9f, thumbSpread = 0.1f),
            practiceTip = "Tuck finger pads snugly against the thumb."
        ),
        GslSignItem(
            id = "alpha_f",
            label = "Letter F",
            category = "Alphabet (A-Z)",
            twiTranslation = "F",
            description = "Touch tip of index finger to tip of thumb forming an 'OK' circle. Extend middle, ring, and pinky fingers straight up.",
            landmarks3D = createPose(thumbCurl = 0.6f, indexCurl = 0.6f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.3f),
            practiceTip = "Maintain a crisp circle between index and thumb."
        ),
        GslSignItem(
            id = "alpha_g",
            label = "Letter G",
            category = "Alphabet (A-Z)",
            twiTranslation = "G",
            description = "Extend index finger and thumb horizontally, parallel to each other like a small pinching mouth. Curl middle, ring, pinky.",
            landmarks3D = createPose(thumbCurl = 0.3f, indexCurl = 0.3f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.4f),
            practiceTip = "Keep palm facing toward your chest horizontally."
        ),
        GslSignItem(
            id = "alpha_h",
            label = "Letter H",
            category = "Alphabet (A-Z)",
            twiTranslation = "H",
            description = "Extend index and middle fingers straight together horizontally. Curl thumb over ring and pinky fingers.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Keep index and middle fingers touching sideways."
        ),
        GslSignItem(
            id = "alpha_i",
            label = "Letter I",
            category = "Alphabet (A-Z)",
            twiTranslation = "I",
            description = "Make a fist with thumb crossing over index/middle/ring fingers. Extend pinky finger straight up.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 0.0f, thumbSpread = 0.2f),
            practiceTip = "Only the pinky extends vertically."
        ),
        GslSignItem(
            id = "alpha_j",
            label = "Letter J",
            category = "Alphabet (A-Z)",
            twiTranslation = "J",
            description = "Form the 'I' posture with extended pinky, then trace a curved 'J' motion in the air with the pinky tip.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 0.0f, thumbSpread = 0.2f),
            practiceTip = "Trace a small swooping hook in the air."
        ),
        GslSignItem(
            id = "alpha_k",
            label = "Letter K",
            category = "Alphabet (A-Z)",
            twiTranslation = "K",
            description = "Extend index finger straight up. Extend middle finger forward. Place thumb tip against middle finger joint.",
            landmarks3D = createPose(thumbCurl = 0.4f, indexCurl = 0.0f, middleCurl = 0.3f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.5f),
            practiceTip = "Forms a V shape with index and middle finger, thumb in middle."
        ),
        GslSignItem(
            id = "alpha_l",
            label = "Letter L",
            category = "Alphabet (A-Z)",
            twiTranslation = "L",
            description = "Extend index finger straight up and thumb straight out to form an L shape. Curl middle, ring, pinky into fist.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 0.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 1.0f),
            practiceTip = "Clear 90-degree angle between thumb and index."
        ),
        GslSignItem(
            id = "alpha_m",
            label = "Letter M",
            category = "Alphabet (A-Z)",
            twiTranslation = "M",
            description = "Tuck thumb underneath index, middle, and ring fingers so thumb tip pokes out between ring and pinky.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 0.8f, middleCurl = 0.8f, ringCurl = 0.8f, pinkyCurl = 1.0f, thumbSpread = 0.1f),
            practiceTip = "Three fingers rest over the thumb."
        ),
        GslSignItem(
            id = "alpha_n",
            label = "Letter N",
            category = "Alphabet (A-Z)",
            twiTranslation = "N",
            description = "Tuck thumb underneath index and middle fingers so thumb tip pokes out between middle and ring fingers.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 0.8f, middleCurl = 0.8f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.1f),
            practiceTip = "Two fingers rest over the thumb."
        ),
        GslSignItem(
            id = "alpha_o",
            label = "Letter O",
            category = "Alphabet (A-Z)",
            twiTranslation = "O",
            description = "Touch all four fingertips to thumb tip forming a complete, rounded O shape.",
            landmarks3D = createPose(thumbCurl = 0.5f, indexCurl = 0.5f, middleCurl = 0.5f, ringCurl = 0.5f, pinkyCurl = 0.5f, thumbSpread = 0.3f),
            practiceTip = "Smooth circular opening visible from front."
        ),
        GslSignItem(
            id = "alpha_p",
            label = "Letter P",
            category = "Alphabet (A-Z)",
            twiTranslation = "P",
            description = "Form the 'K' sign and point it downward toward the ground.",
            landmarks3D = createPose(thumbCurl = 0.4f, indexCurl = 0.3f, middleCurl = 0.5f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.5f),
            practiceTip = "Downward pointing K shape."
        ),
        GslSignItem(
            id = "alpha_q",
            label = "Letter Q",
            category = "Alphabet (A-Z)",
            twiTranslation = "Q",
            description = "Form the 'G' shape (index & thumb parallel) and point both fingers downward.",
            landmarks3D = createPose(thumbCurl = 0.5f, indexCurl = 0.5f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.4f),
            practiceTip = "Downward pointing G shape."
        ),
        GslSignItem(
            id = "alpha_r",
            label = "Letter R",
            category = "Alphabet (A-Z)",
            twiTranslation = "R",
            description = "Cross index finger over middle finger pointing upward. Curl ring, pinky, and thumb into fist.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Cross index over middle like wishing for luck."
        ),
        GslSignItem(
            id = "alpha_s",
            label = "Letter S",
            category = "Alphabet (A-Z)",
            twiTranslation = "S",
            description = "Make a tight fist with all four fingers curled, and place thumb across the front of fingers.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.1f),
            practiceTip = "Thumb rests over the front of knuckles."
        ),
        GslSignItem(
            id = "alpha_t",
            label = "Letter T",
            category = "Alphabet (A-Z)",
            twiTranslation = "T",
            description = "Tuck thumb under index finger so thumb tip pokes out between index and middle finger.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 0.8f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.1f),
            practiceTip = "Single index finger resting over thumb."
        ),
        GslSignItem(
            id = "alpha_u",
            label = "Letter U",
            category = "Alphabet (A-Z)",
            twiTranslation = "U",
            description = "Extend index and middle fingers straight up held together. Curl ring, pinky, and thumb into fist.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Index and middle fingers erect and touching."
        ),
        GslSignItem(
            id = "alpha_v",
            label = "Letter V",
            category = "Alphabet (A-Z)",
            twiTranslation = "V",
            description = "Extend index and middle fingers up separated in a V shape. Curl ring, pinky, and thumb into fist.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Spread index and middle into clear V shape."
        ),
        GslSignItem(
            id = "alpha_w",
            label = "Letter W",
            category = "Alphabet (A-Z)",
            twiTranslation = "W",
            description = "Extend index, middle, and ring fingers up separated like a W. Touch thumb tip to pinky tip.",
            landmarks3D = createPose(thumbCurl = 0.7f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.9f, thumbSpread = 0.4f),
            practiceTip = "Three fingers upright forming W."
        ),
        GslSignItem(
            id = "alpha_x",
            label = "Letter X",
            category = "Alphabet (A-Z)",
            twiTranslation = "X",
            description = "Curl index finger into a hook shape. Curl middle, ring, pinky, and thumb into fist.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.5f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Hooked index finger like a trigger."
        ),
        GslSignItem(
            id = "alpha_y",
            label = "Letter Y",
            category = "Alphabet (A-Z)",
            twiTranslation = "Y",
            description = "Extend thumb and pinky finger straight out. Curl index, middle, and ring fingers into fist.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 0.0f, thumbSpread = 1.0f),
            practiceTip = "Classic 'hang loose' hand gesture."
        ),
        GslSignItem(
            id = "alpha_z",
            label = "Letter Z",
            category = "Alphabet (A-Z)",
            twiTranslation = "Z",
            description = "Extend index finger straight out and trace a 'Z' path in the air.",
            landmarks3D = createPose(thumbCurl = 0.8f, indexCurl = 0.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.2f),
            practiceTip = "Draw Z shape in the air with index fingertip."
        )
    )

    val gslPhraseSigns: List<GslSignItem> = listOf(
        GslSignItem(
            id = "gsl_akwaaba",
            label = "Akwaaba (Welcome)",
            category = "Greetings",
            twiTranslation = "Akwaaba",
            description = "Bring dominant open hand towards chest with palm facing inwards, giving a warm motion of ushering someone in.",
            landmarks3D = createPose(thumbCurl = 0.2f, indexCurl = 0.1f, middleCurl = 0.1f, ringCurl = 0.1f, pinkyCurl = 0.1f, thumbSpread = 0.6f),
            practiceTip = "Hold palm toward chest and motion gently inward.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_thank_you",
            label = "Thank You",
            category = "Greetings",
            twiTranslation = "Medaase",
            description = "Touch fingertips of open hand to chin, then extend hand forward towards the person.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.3f),
            practiceTip = "Start at chin and move hand gently forward.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_hello",
            label = "Hello",
            category = "Greetings",
            twiTranslation = "Agoo / Hello",
            description = "Place open hand near temple and salute outward in a friendly wave gesture.",
            landmarks3D = createPose(thumbCurl = 0.1f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.5f),
            practiceTip = "Salute motion starting from side of forehead.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_help",
            label = "Help",
            category = "Essentials",
            twiTranslation = "Mmoa",
            description = "Place a fist with thumb up on top of an open flat palm, moving both hands upwards together.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.8f),
            practiceTip = "Thumbs-up resting on non-dominant flat palm moving upward.",
            difficulty = "Intermediate"
        ),
        GslSignItem(
            id = "gsl_yes",
            label = "Yes",
            category = "Essentials",
            twiTranslation = "Aane",
            description = "Make an 'S' fist and nod it up and down like a head nodding yes.",
            landmarks3D = createPose(thumbCurl = 0.9f, indexCurl = 1.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.1f),
            practiceTip = "Tilt fist up and down from the wrist.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_no",
            label = "No",
            category = "Essentials",
            twiTranslation = "Daabi",
            description = "Bring index and middle finger together and snap down against thumb tip.",
            landmarks3D = createPose(thumbCurl = 0.6f, indexCurl = 0.6f, middleCurl = 0.6f, ringCurl = 1.0f, pinkyCurl = 1.0f, thumbSpread = 0.4f),
            practiceTip = "Quick snapping motion between index/middle and thumb.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_please",
            label = "Please",
            category = "Greetings",
            twiTranslation = "Mpaabo / Me srɛ wo",
            description = "Place flat open hand over chest and rub in a gentle circular motion clockwise.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.3f),
            practiceTip = "Circular motion over upper chest with flat palm.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_good_morning",
            label = "Good Morning",
            category = "Greetings",
            twiTranslation = "Maakye",
            description = "Sign 'Good' (chin to hand) followed by forearm rising like sun over horizon.",
            landmarks3D = createPose(thumbCurl = 0.1f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.4f),
            practiceTip = "Combine chin release with arm rising gesture.",
            difficulty = "Intermediate"
        ),
        GslSignItem(
            id = "gsl_iloveyou",
            label = "I Love You",
            category = "Greetings",
            twiTranslation = "Medɔ wo",
            description = "Extend thumb, index finger, and pinky finger up (combining 'I', 'L', and 'Y'). Curl middle and ring finger.",
            landmarks3D = createPose(thumbCurl = 0.0f, indexCurl = 0.0f, middleCurl = 1.0f, ringCurl = 1.0f, pinkyCurl = 0.0f, thumbSpread = 1.0f),
            practiceTip = "Thumb, index, and pinky fully extended.",
            difficulty = "Beginner"
        ),
        GslSignItem(
            id = "gsl_stop",
            label = "Stop",
            category = "Essentials",
            twiTranslation = "Gyae / Gyina",
            description = "Chop dominant open hand down sharply onto palm of non-dominant hand.",
            landmarks3D = createPose(thumbCurl = 0.1f, indexCurl = 0.0f, middleCurl = 0.0f, ringCurl = 0.0f, pinkyCurl = 0.0f, thumbSpread = 0.2f),
            practiceTip = "Firm downward stopping motion.",
            difficulty = "Beginner"
        )
    )

    val allSigns: List<GslSignItem> = alphabetSigns + gslPhraseSigns
}
