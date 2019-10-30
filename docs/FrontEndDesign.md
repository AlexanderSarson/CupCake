### Commands

!!! hint Home
  **Execute**
  - Checks if products exists in application scope
    - no: calls LogicFacade.getPremadeCupcakes();
      - returns List of PremadeCupcakes
      - sets List of Premades in application scope
      - forward to ShopPremade
    - yes, forward to ShopPremade
!!!

!!! hint Login  
  **Execute**
  - Get email, password from request
  - Send user info to LogicFacade.login(Email, Password)
  - Return User object or Exception
    - Store user object in session scope
    - Prints exception message to user
  - Forward to home
!!!

!!! hint Register
  **Execute**
  - Get user name, email, password from request
  - Send user info to LogicFacade.newUser(name, email, password)
  - Return User object or Exception
    - Store user object in session scope
    - Prints exception message to user
  - Forward to home
!!!

!!! hint AddToCart
  **Execute**
  - Check if shopping cart exist
    - **no**: creates shopping cart by calling LogicFacade.getShoppingCart() - returns empty shopping cart object
      - Calls addToShoppingCart(Top, Bottom, ShoppingCart) - void
    - **yes**: Calls addToShoppingCart(Top, Bottom, ShoppingCart) - void
!!!

!!! danger ViewShoppingCart
  **NOT A COMMAND**
  Prints orderlines from shopping cart to the user. User can edit or remove orderlines
  
  - Remove order line
    - sessionScope.shoppingCart.removeOrderLine()
  - Edit orderline
    - sessionScope.shoppingCart.getOrderLine().setQuantity()

!!!

!!! danger Proceed To Checkout
  **NOT A COMMAND**
  Prints orderlines once again in order for the user to finally confirm order. User *cannot* edit orderlines.
!!!

!!! hint Confirm Order
  **Execute**
  - Send shopping cart to LogicFacade.submitOrder(ShoppingCart)
  - Returns Order || Exception
  - Store order in session
  - Forward to invoice.jsp
!!!

!!! hint Pages 

* index / landing / home
* nav
* Register new user
* CustomerPage
* ViewOrder
* ShoppingCart
* OrderConfirmation
* Invoice
* ShopPremade
* ShopCustom
* 


!!! hint LogicFacade
```java
  User login(Email, Password);
  User newUser(String name, String email, String password);
  ShoppingCart getShoppingCart();
  List<Cupcake> getPremadeCupcakes();
  void addToShoppingCart(Topping topping, Bottom bottom, ShoppingCart shoppingCart);
  Order submitOrder(User user, ShoppingCart shoppingCart);
  List<Order> getOrdersForUser(User user);
  addFunds(User user, int amountToDeposit);
  updateUser(User user);
  
```
!!!

