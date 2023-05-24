# Aisle Assignment

>**Project 1**: Aisle has millions of users from over 150 countries. Each user gets 10 free daily Likes. Unused Likes are not carried forward to the next day. We must refresh the number of daily Likes at 12:00 pm according to the user's local time.
How would you go about doing this in a scalable way? No need to write code, simply explain to us in theory the backend logic in your own words.
___

To ensure an efficient process of refreshing daily Likes for millions of users from over 150 countries, the backend employs various techniques. Firstly, it gathers essential details from the user's device, including timezone and location, which can be determined through IP address. Utilizing this information, the backend acts accordingly, providing current like counts if the user is within their specific time window. As the designated time (12 pm) approaches, the backend updates the user's like count to the daily maximum limit (10 likes). This updated count is then delivered to the client through an API, often called during the app's initial dashboard request or via a job scheduler/work manager executed every 15 minutes. Additionally, the backend can send a notification to inform the user that their like counts have been reset. Through these informative measures, the backend ensures smooth and timely like count refreshment for users across diverse timezones.
