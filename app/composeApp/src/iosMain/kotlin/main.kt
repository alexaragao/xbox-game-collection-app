import androidx.compose.ui.window.ComposeUIViewController
import com.xboxgamecollection.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
