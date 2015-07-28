# jvm-types

A playground for gathering examples of types that we use and don't want to re-implement in every project.

## Why?

A question we are thinking of is, is it possible to create a jvm library that contains
the types you need in your typical web application?

We think that it might be possible. This is a project where we can explore the ideas.

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

## Deployment

Let us build something before we try to deploy it somewhere.
