# JEE Project

## Environment
+ Intellij 2020.2.3
+ Maven
+ Java 15
+ Tomcat 9.0.41

## Launch project
+ Launch Intellij
+ Add Project Version Control
+ Add Configuration ... :
    + First : Select Tomcat Server
    + Then : Add Tomcat Server file, Select Navigator and Add Artifact war
+ Build and Run
 
# Work 

## Views
+ [ ] Menu : links
  + [ ] Home
  + [ ] Login
  + [ ] Sign up
  + [x] Logout
+ [ ] Panier :
  + [ ] list of products small detail (name, quantity, price) and option delete ?
  + [ ] Price total
  + [ ] button validate panier
+ [ ] Add Product :
  + [ ] Form new product
+ [ ] Validate panier :
  + [ ] recap
  + [ ] options (livraison, facture?)
  + [ ] button pay
+ [ ] Pay now :
  + [ ] Form info paiement
  + [ ] button pay
+ [ ] Commandes :
  + [ ] liste commandes
    + [ ] mode paiment
    + [ ] mode livraison
    + [ ] price

## Global features
+ [x] tagdir : remove the jsp first line
+ [x] listner servlet
+ [x] add relevant library or dependencies :
  + **F4** on project 
  + go to **dependencies** in **modules**
  + **+** then choose **library** then **From Maven**
  + Just search your dependency, download it, add it and **Apply**
  


## Core
+ [ ] beans : add cookies for cart
+ [x] dao
+ [ ] model : commands ?
+ [x] controllers

# TODO
+ [ ] add model for commands and in database too
+ [ ] add SQL request : commands of user, add product
+ [ ] so much more