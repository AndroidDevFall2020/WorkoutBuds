
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
Due to the commencement of covid 19, College athletes have been unable to participate in various trainings and activities thereby causing loss in body shape and tuition scholarships. Our app, Workout Buds, brings athletes all over the state together to help stay in shape while also having a fun comradery while also preserving scholarships. To ensure this, our app makes use of ML and poseestimation models to examine each workout the user performs and automatically counts the number of reps he/ she performs.

### App Evaluation
- **Category:** Fitness & Social
- **Mobile:** Android Mobile App
- **Story:** Workout Buds brings athletes all over the state together to help stay in shape while also having a fun comradery while also preserving scholarships.
- **Market:** Athletes / Users that haven't trained since the pandemic began would find this app useful as they could once again par with others to get fit
- **Habit:**  Could be used through out the day time
- **Scope:** Would easily spread across the state to connect with more Users to train and get in shape

## Product Spec

### 1. User Stories (Required and Optional)

#### Required Must-have Stories
- [x] User can Sign Up
- [x] User can click sign up button
- [x] User can Login
- [x] User can Logout
- [x] User can view list of group chats
- [x] User can click to navigate to group chat
- [x] User can chat in different group chats
- [x] User views a list of different posts
- [x] User can view details of the chat
- [x] User can tap to view chat details
- [  ] User can view other's profiles
- [  ] User can workout with others
- [  ] User can view his profile
- [  ] User can keep track of his records
- [  ]User can work out alone

**Optional Nice-to-have Stories**
* User can view google maps to view others
* User can like posts
* User can request to join a group chat
* User can use computer vision to predict workout count


### 2. Screen Archetypes

* Login Screen
   * User can login 
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
   * User can challenge other users
   * User can Workout alone
* Chat Screen
   * User can chat with other Users
   * User can tap to view chat details
* Chat Details
   * User can view details of the chat
   * User can click on other user profile to view their  profiles
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

### Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://g.recordit.co/ZVrMLdSf56.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='http://g.recordit.co/Kv5DtgXLNa.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='http://g.recordit.co/YYoI7nyQNw.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src ='https://recordit.co/drA4GPyayJ.gif' title = 'Video Walkthrough' width = '' alt = 'Video Walkthrough' />
<img src ='http://g.recordit.co/Fp3kkO0pdA.gif' title = 'Video Walkthrough' width = '' alt = 'Video Walkthrough' />
<img src ='http://g.recordit.co/5jkSr8gHQR.gif' title = 'Video Walkthrough' width = '' alt = 'Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Schema 
### Models
#### GroupChat Screen
| Property  | Type           |Description |
| --------  | --------       | --------   |
| objectId  | String         | unique id for the user post        |
|author     | Pointer to User| pointer to username of the user    |
|description| String         | description made by author      |
|createdAt  | DateTime       | date when post was made|
|groupName  | String         | states the group name|
|image      | File           | image corresponding to the group   |
|members    | Array          | members of groupchat|

#### Post 
| Property | Type           |Description |
| -------- | --------       | --------   |
| objectId | String         | unique id for the user post|
|author    | Pointer to User| pointer to username of the user|
|caption| String| caption made by author|
|createdAt| DateTime| date when post was made|
|image    | file    | image file uploaded |


#### Users
| Property | Type      | Description |
| -------- | --------  | -------- |
| objectId | String    | Unique id for user    |
| username | String    | name the user has |
|password  | String    | password associated with user
|createdAt | DateTime  | Date user was made |
|Activities| Dictionary| Updates to workout counts|
|School    | String    | School user attends|

#### Chat
| Property | Type       | Description|
| -------- | --------   | --------   |
| objectId | String     | unique id for the chat   |
|createdAt | DateTime   | time the text was sent|
|chatName  | String     | name of the chat |
|author    |ClassPointer| pointer to messanger|







### Networking
#### Network Request Outlines
* GroupChat Screen
   * (Read/GET) Query all groupchats
   * (Create/POST) Create a new groupchat
   * (Create/POST) Join a groupchat
* Post Screen
   * (Read/GET) Query all posts
* Profile Screen
   * (Read/GET) Query user info in user class
* Chat Screen
   * (Read/GET) Query all chats
   * (Create/POST) Create a new chat message
* Chat Details
   * (Read/GET) Query all users in groupchat
* Workout Screen
   * (Create/POST) new workout count
* Challenge Screen
   * (Create/POST) new workout count
#### Parse Snippets
| Parse Method  |      Example      |
|   ---         |       ---         |
|https://docs.parseplatform.org/android/guide/#saving-objects| Creating a new post|
|https://docs.parseplatform.org/android/guide/#queries| Fetching posts for a user's feed|
|https://docs.parseplatform.org/android/guide/#updating-objects| Changing a user's profile image and uploading chats|

