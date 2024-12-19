package com.example.afinal.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.afinal.repository.provideProductRepository
import com.example.afinal.repository.provideShoppingCartRepository
import com.example.afinal.ui.screens.CartScreen
import com.example.afinal.ui.screens.LoginScreen
import com.example.afinal.ui.screens.ProductDetailsScreen
import com.example.afinal.ui.screens.ProductListScreen
import com.example.afinal.viewmodel.ProductViewModel
import com.example.afinal.viewmodel.ShoppingCartViewModel

@Composable
fun ShoppingNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(
                viewModel = ProductViewModel(provideProductRepository(context)),
                onProductClick = { productId ->
                    navController.navigate("productDetails/$productId")
                }
            )
        }
        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailsScreen(
                productId = productId,
                viewModel = ProductViewModel(provideProductRepository(context)),
                cartViewModel = ShoppingCartViewModel(provideShoppingCartRepository(context)),
                onAddToCart = { product ->
                    navController.navigate("cart")
                },
            )
        }
        composable("cart") {
            CartScreen(
                viewModel = ShoppingCartViewModel(provideShoppingCartRepository(context)),
                onCheckout = { }
            )
        }
        composable("login") {
            LoginScreen(
                onLogin = { email, password ->
                },
                onSignUp = {
                    navController.navigate("signUp")
                }
            )
        }
    }
}