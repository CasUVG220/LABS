package com.labsplataformasm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.labsplataformasm.nav.CharacterDetail
import com.labsplataformasm.nav.Characters
import com.labsplataformasm.nav.Login
import com.labsplataformasm.screens.characters.CharactersScreen
import com.labsplataformasm.screens.detail.CharacterDetailScreen
import com.labsplataformasm.screens.login.LoginScreen
import com.labsplataformasm.ui.theme.LabsPlataformasMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsPlataformasMTheme {
                AppNav()
            }
        }
    }
}

@Composable
private fun AppNav() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Login
    ) {
        // Login
        composable<Login> {
            LoginScreen(
                onStart = {
                    nav.navigate(Characters) {
                        popUpTo(Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Characters> {
            CharactersScreen(
                onOpenDetail = { id ->
                    nav.navigate(CharacterDetail(id))
                }
            )
        }

        // Detalle de personaje
        composable<CharacterDetail> { entry ->
            val args = entry.toRoute<CharacterDetail>()
            CharacterDetailScreen(
                id = args.id,
                onBack = { nav.navigateUp() }
            )
        }
    }
}
