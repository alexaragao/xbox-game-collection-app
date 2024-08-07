package com.xboxgamecollection.app.navigation

enum class AppScreen(val title: String) {
    Start(title = "app_start"),
    Home(title = "app_home"),
    GameList(title = "app_game_list"),
    GameDetails(title = "app_game_details/{gameId}"),
    Profile(title = "app_profile"),
    SignIn(title = "app_sign_in"),
    Register(title = "app_register"),
    BarcodeScanner(title = "app_barcode_scanner"),
    ScanResult(title = "app_scan_result/{barcode}"),
}
