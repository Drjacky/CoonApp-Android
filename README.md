<p align="center">
<img src="jodellogo.png" width="300">
</p>

Jodel
==================================

Hi there! So, you would like to apply to join the Jodel Android Team? We're honored to have you here!

Your assignment is to provide us with some meaty code sample, so we have something to talk about during our delightful discussion. If you already have a project you'd like to show to us, please let us know. If you don't, no problem! We have a quite interesting task for you to solve.

## Challenge Accepted

Compile the app and open it, you will see a list of todos fetched from [User's task](https://jsonplaceholder.typicode.com/todos?userId=1). Your assignment is to improve the app and make it an enjoyable users' experience. For your assignment, you are supposed to finish a set of tasks below:

1. Add a bottom navigation in the app so users are able to switch between different views (user profile/photos /tasks)
2. Implement a photo list and add it to bottom navigation, for showing current user's photos in a List-structured View.
Hint: using both [Albumns](https://jsonplaceholder.typicode.com/albums?userId=1) and [Photos](https://jsonplaceholder.typicode.com/photos?albumId=1) to paginate the long list of photos by album
3. Implement a user profile view with the current _login_ user [User's profile](https://jsonplaceholder.typicode.com/users) and meanwhile listing all 10 users below. So whenever a user's selected, the user profile should be updated and all photos and tasks should be updated accordingly as well.

[Bonus]
1. Show us how Kotlin could make code much leaner.
2. Add some UI tests with mocked response

We've setup some boilerplate code for you as tips, but the requirements and specification are quite open. This directly represents how we work here: we have guidelines, but every team member is open to introduce improvements to any area of the app.

Remember, our goal is not to *compare* and *grade* the projects, but to get to know you better. We care about the quality, not quantity, we don't want you to waste your precious time.

### Interface

We don't *"grade"* projects based on how much time you've spent designing and polishing UI so don't waste time on that. All we care about is *tidiness* of the UI: equal margins, logical view hierarchy, logical layout constraints.

Also Animation is welcome, but not mandatory.

### Architecture

• We would like you to use the MVP architecture for presentation layer and meanwhile structure the app in 3-layer. Try to avoid mixing business and presentation logic, there is usecase layer created for you.

• The project should be very easy to unit test. Cover the _user photo list_ in unit tests

• If you feel the current architecture somehow limit the usage of reactive, feel free to speak out your thought in code

• Feel free to use any third-party libraries

### Other requirements

• Currently Retrofit is not debug friendly, make it log in trace stack

• Each photo is shown in a CardView, other details are shown below the CardView.

• Submit your assignment via Pull Request

**Good luck**!




