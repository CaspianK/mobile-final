package com.example.afinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.models.CartItem
import com.example.afinal.models.ShoppingCart
import com.example.afinal.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val shoppingCartRepository: ShoppingCartRepository) : ViewModel() {

    private val _cart = MutableStateFlow<ShoppingCart?>(null)
    val cart: StateFlow<ShoppingCart?> = _cart

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun fetchCart(userId: Int) {
        viewModelScope.launch {
            _cart.value = shoppingCartRepository.getOrCreateShoppingCart(userId)
        }
    }

    fun addOrUpdateCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            shoppingCartRepository.addOrUpdateCartItem(cartItem)
            _cartItems.value = shoppingCartRepository.getCartItems(_cart.value!!.id)
        }
    }

    fun removeCartItem(cartItemId: Int) {
        viewModelScope.launch {
            shoppingCartRepository.removeCartItem(cartItemId)
            _cartItems.value = shoppingCartRepository.getCartItems(_cart.value!!.id)
        }
    }

    fun addToCart(productId: Int, quantity: Int = 1) {
        viewModelScope.launch {
            val currentCart = _cart.value ?: return@launch
            val newCartItem = CartItem(cartId = currentCart.id, productId = productId, quantity = quantity)

            val success = shoppingCartRepository.addOrUpdateCartItem(newCartItem)
            if (success) {
                _cartItems.value = shoppingCartRepository.getCartItems(currentCart.id)
            }
        }
    }
}