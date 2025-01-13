# MA Application Idea

## The basics:

### What the app works with:
The app works with music albums that users want to catalog in their
personal collection. Each album contains details such as:
 - Artist: The musician or band responsible for the album.
 - Album Title: The name of the album.
 - Release Year: The year the album was released.
 - Album Cover (optional): A cover image to visually represent the album.

### What the app does:
The app allows users to manage their music album collection by enabling the following operations:
 - View (Read): Users can view their full list of albums and see details
   of individual albums.
 - Add (Create): Users can add new albums to their collection by providing
   relevant details (artist, title, release year, cover art).
 - Update (Edit): Users can update the details of albums they have already added.
 - Delete: Users can remove albums from their collection with a confirmation prompt.

Users have the ability to create accounts and keep their collection backed up across
different devices.  
## Screens:  
### Read Mode:  
Displays a list of all albums in the user’s collection.  
Users can browse their albums and tap on an album for more details.  
![MA Design Read All.png](design%2FMA%20Design%20Read%20All.png)  
The 'add' button, when pressed, opens the [create-mode](#create-mode)  
The 'edit' button, when pressed, opens the [edit-mode](#edit-mode)  
The 'edit' button, when pressed, opens the [delete-mode](#delete-mode)  
The 'view' button opens up a detailed view of the selected album.  
From there, the user can interact with the album further by pressing 'edit' or 'delete'.  
The effect of pressing there buttons here is the same as if they were pressed on the main page.  
These effects will be detailed later.  
Pressing the 'back' button will take the user back to the page with all of the albums.  
![MA Design Read One.png](design%2FMA%20Design%20Read%20One.png)  
### Create Mode:
Displays a form which the user gets to fill out with all relevant information for an album.  
The action can be cancelled and the form closed by pressing the 'cancel' button.  
If the user wants to submit the album and add it to their collection,  
this can be achieved by pressing 'add' on the form.  
![MA Design Create.png](design%2FMA%20Design%20Create.png)  

### Edit Mode:
Presents a form pre-populated with the data from the respective album.  
The user can choose to edit any of the fields and then can submit or cancel the change  
by pressing on the respective buttons.  
This mode is entered by pressing 'edit' on either the mini-view of the album in the main  
page or the detailed view.  
![MA Design Update.png](design%2FMA%20Design%20Update.png)

### Delete Mode:
Presents a pop-up asking the user if he is sure about deleting the album.  
This mode is entered by pressing 'delete' on either the mini-view of the album in the main  
page or the detailed view.  
Upon confirming, the album will be deleted from the user's list.  
![MA Design Delete.png](design%2FMA%20Design%20Delete.png)  
![MA Design Delete One.png](design%2FMA%20Design%20Delete%20One.png)  

### Sign Up Mode:
In order to persist a collection across devices, the users have the possibility to create an 
account.  
This is fulfilled by filling out the form below:  
![MA Design Sign Up.png](design%2FMA%20Design%20Sign%20Up.png)  

### Log In Mode:
In order to log into an already-existing account, a user can simply log in via the form below.  
![MA Design Log In.png](design%2FMA%20Design%20Log%20In.png)  

## Storage:
### Local:
When offline, new albums, as well as edits on existing albums and deletes  
are persisted locally.  
These CRUD (Create, Read, Update, Delete) operations will be synced to the  
server once the device goes back online.  
 - **Create**: New albums are saved locally on the device when created.  
   The album will be uploaded to the server once connectivity is restored.
 - **Read**: All saved albums can be accessed and viewed offline as the app  
   loads the album data from the local database.
 - **Update**: When an album is updated offline, the changes are saved locally  
   and later synchronized with the server once the device reconnects to the internet.
 - **Delete**: Deleting an album while offline removes it from the local database,  
   and the change will be synced with the server later.
### Server:
When the device is connected to the internet, the album collection is backed up on a remote server.  
This allows users to log in from multiple devices and have access to their synced album  
collection across all platforms.
If CRUD operations (Create, Update, Delete) are performed  
offline, these are automatically synced with the server once the app detects an internet connection.

 - **Create**: New albums created while online are immediately sent to the server  
   to ensure the user's collection is stored remotely and available for access on other devices.
 - **Read**: When the device is online, the app can fetch albums directly from the  
   server to ensure the collection is up-to-date across all logged-in devices.
 - **Update**: Album updates are sent to the server in real time when the app is online,  
   allowing users to see changes on all devices.
 - **Delete**: Deleting an album while online instantly removes it from both the local  
   database and the server.
 
## Offline options:
* **Create**:
  * Users can create a new album while offline.  
    The album will be stored locally on the device's database.
  * Once the device goes online, the new album will be automatically  
    synced with the server and backed up remotely.

* **Read**:
  * Users can still view all albums that have been previously saved locally.
    This allows them to browse their collection even when no internet connection is available.
  * If any changes were made on another device while the app was offline, the album list  
    will update and sync when the device is reconnected to the internet.

* **Update**:
  * Users can edit albums while offline, and the changes will be saved locally.
  * When the app is online again, it will sync the changes to the server so that  
    updates reflect on all devices using the same account.

* **Delete**:
  * Users can delete albums while offline. The app will remove the album from the
    local database immediately.  
  * When the device goes online, the deletion will sync with the server to ensure  
    that the album is removed from the user’s collection across all devices.