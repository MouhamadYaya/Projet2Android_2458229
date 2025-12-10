package com.example.projet2android_2458229.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.projet2android_2458229.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val BodyFont = FontFamily(
    Font(
        googleFont = GoogleFont("Andika"),
        fontProvider = provider
    )
)

val DisplayFont = FontFamily(
    Font(
        googleFont = GoogleFont("Acme"),
        fontProvider = provider
    )
)

val AppTypography = Typography(
    displayLarge = Typography().displayLarge.copy(fontFamily = DisplayFont),
    displayMedium = Typography().displayMedium.copy(fontFamily = DisplayFont),
    displaySmall = Typography().displaySmall.copy(fontFamily = DisplayFont),

    headlineLarge = Typography().headlineLarge.copy(fontFamily = DisplayFont),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = DisplayFont),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = DisplayFont),

    titleLarge = Typography().titleLarge.copy(fontFamily = DisplayFont),
    titleMedium = Typography().titleMedium.copy(fontFamily = DisplayFont),
    titleSmall = Typography().titleSmall.copy(fontFamily = DisplayFont),

    bodyLarge = Typography().bodyLarge.copy(fontFamily = BodyFont),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = BodyFont),
    bodySmall = Typography().bodySmall.copy(fontFamily = BodyFont),

    labelLarge = Typography().labelLarge.copy(fontFamily = BodyFont),
    labelMedium = Typography().labelMedium.copy(fontFamily = BodyFont),
    labelSmall = Typography().labelSmall.copy(fontFamily = BodyFont),
)
