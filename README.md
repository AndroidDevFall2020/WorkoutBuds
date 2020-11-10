Original App Design Project - README Template
===

# Workout Buds

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Due to the commencement of covid 19, College athletes have been unable to participate in various trainings and activities thereby causing loss in body shape and tuition scholarships. Our app, Workout Buds, brings athletes all over the state together to help stay in shape while also having a fun comradery while also preserving scholarships.

### App Evaluation
- **Category:** Fitness & Social
- **Mobile:** Android Mobile App
- **Story:** Workout Buds brings athletes all over the state together to help stay in shape while also having a fun comradery while also preserving scholarships.
- **Market:** Athletes / Users that haven't trained since the pandemic began would find this app useful as they could once again par with others to get fit
- **Habit:**  Could be used through out the day time
- **Scope:** Would easily spread across the state to connect with more Users to train and get in shape

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* User can Sign Up
* User can Login
* User can Logout
* User can view list of group chats
* User can chat in different group chats
* User can view other's profiles
* User can workout with others
* User can view his profile
* User can keep track of his records
* User can work out alone

**Optional Nice-to-have Stories**
* User can view google maps to view others
* User can like posts
* User can request to join a group chat
* User can use computer vision to predict workout count


### 2. Screen Archetypes

* Login Screen
   * User can login 
   * User can view app's logo
   * User can click sign up button
* Sign Up Screen
   * User can sign up to create account
   * User login after hitting sign up button
   * User can go back to login page
* Timeline Screen
   * User views a list of group chats
   * User can click to navigate to group chat
* Post Screen
   * User views a list of different posts
* Profile Screen
   * User can view his profile
   * User can keep track of his work out counts
   * User can view stats
   * User can Workout alone
* Chat Screen
   * User can chat with other Users
   * User can tap to view chat details
* Chat Details
   * User can view details of the chat
   * User can challenge other users
   * User can click on other user profile to view their   profiles
* Challenge Screen
   * User can challenge other users to workout with them
   * User sees winner in the end
   * Score gets added to users stats
* Workout Alone Screen
   * User can workout alone 


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Group chat Screen
* Post Screen
* Profile Screen

**Flow Navigation** (Screen to Screen)

* Login Screen
   * SignUp Page
   * GroupChat Screen
* SignUp Screen
   * Login Screen
   * GroupChat Screen
* GroupChat Screen
   * Post Screen
   * Profile Screen
   * Chat Screen
* Chat Screen
   * Chat Detail Screen
   * Group Chat Screen
* Chat Detail Screen
   * Chat Screen
   * Challenge Screen
* Post Screen
   * GroupChat Screen
   * Profile Screen
* Profile Screen
   * GroupChat Screen
   * Post Screen
   * WorkOut Alone Screen
* Challenge Screen
   * Chat Detail Screen
* WorkOut Alone Screen
   * Profile Screen
   


## Wireframes
<img src="https://i.imgur.com/MQPHRca.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 

### Models
#### Posts
| Property        | Type        | Description  |
| ------------- |:-------------|:-----------------------------------|
| objectId      | String       | id given to the user post          |
| author        |  Pointer to User|  shows username for who made the post|
| Description | String      | description of post made by user          |
| createdAt   | DateTime    | date when post was made |
| updatedAt  |  
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
