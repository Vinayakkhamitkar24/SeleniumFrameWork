Feature: Purchase Order from Ecommerce website

	Background: 
	Given I landed on Ecommerce page
	
	@Regression
  Scenario Outline: Positive Test for purchasing order
    Given Logged in with username <name> and password <password>
    When  I add product <ProductName> to cart
    And   Checkout <Productname> and submit order
    Then  "THANKYOU FOR THE ORDER." message displayed on confirmation page

    Examples: 
      |name  						 |password	|ProductName |
      |rastogi@gmail.com |Vck@240397|ZARA COAT 3 |
       
