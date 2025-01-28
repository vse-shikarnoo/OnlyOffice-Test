package kv.test.office

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kv.test.office.ui.AuthScreen
import kv.test.office.ui.navigation.BottomBar
import kv.test.office.ui.navigation.NavigationGraph
import kv.test.office.ui.theme.OfficeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfficeTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }

                Scaffold(
                    bottomBar = {
                        if (buttonsVisible) {
                            BottomBar(
                                navController = navController,
                                state = buttonsVisible,
                                modifier = Modifier
                            )
                        }
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        NavigationGraph(navController) { isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }
}
