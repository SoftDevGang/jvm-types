# jvm-types

A playground for gathering examples of types that we use and don't want to re-implement in every project.

## Goal (as defined by Alex)

Looking for ways to mistake-proof code using types. 

For example, in web apps, the input is typically a string; 
I immediately transform it into more specific types, be it primitive (date, number) 
or complex (money, temperature etc.). 
But could we do better? 
One example is bounded types for values that only make sense in an interval. 

### Library of JVM Types?

And the next question is: are there libraries that support this style of coding for JVM? 
Or should I start extracting one from my code ;).

Is it even possible to create a jvm library that contains
the types you need in your typical web application?

We think that it might be possible. This is a project where we can explore the ideas.

### Usage of such a library

Another question is, would you use this library if it exists?

The types might be a part of your core domain. Should you allow a third party library to be used
in the heart of your core domain? The answer here differs. some people might, some may not.
It all depends and we will not know unless we create something that can be tried.

## Developing

### Build

Build the project using Gradle. Do

```
./gradlew clean build --daemon
```
in the project directory. Gradle and all dependencies will be downloaded.

### Deployment

Let us build something before we try to deploy it somewhere.
