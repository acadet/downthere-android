# Downthere Android App

> 'Cause UpThere was too mainstream.

An Android app sample using my latest skillset. [Uses this server as an API](https://github.com/acadet/downthere-server).

## Architecture

### UI

* Activities
* Adapters
* Events: used by the different event buses.
* Fragments

### Model

* BLL - Business logic layer. Bridge between UI and other model layers.
* DAO: data access layer. Used for caching data.
* Service layer: groups the different used services (here only the remote API).

## Libraries

* [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations)
* [ButterKnife](http://jakewharton.github.io/butterknife/)
* [Crouton](https://github.com/keyboardsurfer/Crouton)
* [Dagger 2.0](https://github.com/google/dagger)
* [EventBus](https://github.com/greenrobot/EventBus)
* [JodaTime](http://www.joda.org/joda-time/)
* [Realm](http://realm.io/)
* [RetroFit](http://square.github.io/retrofit/)
* [RetroLambda](https://github.com/orfjackal/retrolambda)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [SmoothProgressBar](https://github.com/castorflex/SmoothProgressBar)
* [Stream](https://github.com/aNNiMON/Lightweight-Stream-API)
* [Timber](https://github.com/JakeWharton/timber)

