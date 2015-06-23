#Venuenator
Phunware Interview Homework Assignment

![alt tag](http://oi62.tinypic.com/mcx5x0.jpg)

## Requirements

1. Consume JSON feed (https://s3.amazonaws.com/jon-hancock-phunware/nflapi-static.json)
2. App should be master/detail type app.
	a. ListView shows all venues, and details of a venue when tapped.
3. Use any 3rd party library
4. Run on both handset devices and larger Android devices (7" or 10" tablets)
5. Should work on Android 2.3+
6. Venue may/may not have image associated. If there is an image, display it to the user.
7. Venue may/may not have schedule. If there is a schedule, display it to the user.
8. When share button is pressed, share text should be composed of current venue's name/address.

## Application Description / Discussion

	For this assignment, I used most of the recommended libraries. To handle the JSON object and 
	RESTful service, I used Retrofit. I created an API interface to map the JSON returned from the 
	GET query to a list of Venue POJOs. Initially, I wasn't too familiar with Retrofit so I burnt 
	through quite a few hours reading the documentation and digging through stackoverflow. At first, 
	I used the provided POJOs from the gist and tinkered with GSON's @SerializedName field. 
	I thought that I could use GSON's setFieldNamingStrategy but the JSON object's naming 
	strategy was inconsistent. For most of the early development I used @SerializedName for the 
	POJOs but it seemed inefficient. I ended up shoving the JSON file through 
	http://www.jsonschema2pojo.org/ and created two POJOs with the correct JSON name mapping.
	
	For the Venue JSON objects that actually had an image url, I used Square's Picasso to load the 
	image into the ImageView in my layout. For objects with the url, I found a "default" image I 
	thought looked nice and loaded that instead. I felt that if I left the Venue without an image, 
	it looked rather stale. 
	
	For objects that actually had a schedule, I noticed that the time zone was set to -0800 (PST). 
	I assumed that the time zone provided as part of the object's schedule's start/end date was 
	supposed to translate to the device's local time (rather than time zone provided is actually GMT, 
	which doesn't really make sense why -0800 is there).  To do this, I grabbed the date as a string 
	initially. I created a SimpleDateFormat object to convert the string back to a Date object. Using 
	that Date object, I mapped it back to the appropriate time zone by using TimeZone.getDefault(). 
	
	This application assumes that if the device fits the criteria of xlarge, sw600dp, or bigger, the 
	master/detail layout will load. Smaller devices (small tablets, phones) will load just a ListView; 
	on tap, the appropriate activity with the selected Venue will load. For the smaller size devices, 
	the activity displaying the Venue detail is actually a ViewPager. The "Share" icon should work 
	accordingly. On the larger layout (master/detail) a refresh button and share button are available 
	in the toolbar at the top. However, if just a ListView is displayed, the toolbar will just have a 
	refresh button. Once the user selects a Venue item off the list, the toolbar will change to just 
	the "Share" button. The refresh button will query the RESTful service again and update the list.
	
	To add more functionality, I felt that it was appropriate to add Google Maps features. This is 
	pretty straightforward; once a Venue item is chosen, the coordinates is passed into the Google 
	Maps API and the map is displayed. The user can zoom in/out, get directions to the
	venue, and perform the other standard Maps interactions.

	Overall, I think this assignment took about 3 to 5 hours a day for a week. I think I spent too much
	time getting stuck on Retrofit. If I spent more time, I think it's worth making the layout a bit more
	responsive. I'd probably use Butterknife make the code a bit cleaner.
	
## Credits (Libraries Used)
	
	Libraries Used:
		1. Retrofit - http://square.github.io/retrofit/
		2. Picasso - http://square.github.io/picasso/
		3. GSON - https://code.google.com/p/google-gson/ (Sort of since it's used by Retrofit)
		4. Google Maps - https://developers.google.com/maps/
	
	Tools: 
		1. http://www.jsonschema2pojo.org/ - to create POJOs from JSON
		2. http://makeappicon.com/ - to make app icons
		3. Paint - for all my graphics
		
	Miscellaneous
		1. stackoverflow - for all my questions
		2. Android Programming: The Big Nerd Ranch Guide - Book I used to learn Android.








