package com.bensdevelops.myGOT.ui.screens.flashcardscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bensdevelops.myGOT.core.base.ViewData
import com.bensdevelops.myGOT.ui.screens.flashcardscreen.viewdata.FlashCardViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardViewModel @Inject constructor() : ViewModel() {
    private var _viewData =
        MutableLiveData<ViewData<FlashCardViewData>>(ViewData.Data(flashCardList.random()))
    val viewData: LiveData<ViewData<FlashCardViewData>> get() = _viewData
    fun onNextQuestionClick() {
        viewModelScope.launch {
            _viewData.value = ViewData.Loading()
            _viewData.value = ViewData.Data(flashCardList.random())
        }
    }
}

val flashCardList = listOf(
    FlashCardViewData(
        question = "What is Android Jetpack Compose, and why was it introduced?",
        answer = "Android Jetpack Compose is a modern UI toolkit introduced by Google for building native Android applications. It was introduced to simplify and enhance the UI development process by providing a declarative and reactive approach to building UIs."
    ),
    FlashCardViewData(
        question = "Explain the difference between View-based UI framework and Jetpack Compose.",
        answer = "The View-based UI framework uses XML layout files and imperative code to create and manage UI components. Jetpack Compose, on the other hand, uses a declarative approach where UI components are described as functions of the application state. Compose simplifies UI development, reduces boilerplate code, and provides a more intuitive way to create interactive UIs."
    ),
    FlashCardViewData(
        question = "What are the advantages of using Jetpack Compose over the traditional View system?",
        answer = "Advantages of Jetpack Compose include:\n\nDeclarative UI: Compose allows developers to describe the UI based on the current state, making the code more concise and readable.\nLive Previews: Compose offers real-time UI previews in Android Studio, enabling instant visual feedback during development.\nSimplified State Management: Compose simplifies state management by automatically recomposing only the affected parts of the UI when the state changes.\nEnhanced Performance: Compose leverages efficient rendering and diffing algorithms, resulting in improved performance compared to the traditional View system."
    ),
    FlashCardViewData(
        question = "What is a Composable function in Jetpack Compose?",
        answer = "A Composable function is a regular Kotlin function annotated with the @Composable annotation. It is the fundamental building block in Jetpack Compose and describes a UI component's appearance and behavior. Composable functions are independent of the activity or fragment lifecycle, making them reusable and testable."
    ),
    FlashCardViewData(
        question = "How does state management work in Jetpack Compose?",
        answer = "State management in Jetpack Compose revolves around the concept of mutable state. Compose provides the mutableStateOf function to create observable state objects. When the state changes, Jetpack Compose automatically recomposes only the affected parts of the UI, ensuring efficient UI updates."
    ),
    FlashCardViewData(
        question = "What is the role of the Modifier in Jetpack Compose?",
        answer = "The Modifier is used to customize and apply transformations to UI elements in Jetpack Compose. It allows you to specify properties such as size, padding, alignment, background color, and more. Modifiers can be chained together to apply multiple transformations to a single UI element."
    ),
    FlashCardViewData(
        question = "How is navigation handled in Jetpack Compose?",
        answer = "Navigation in Jetpack Compose is handled using the Navigation Compose library. It introduces the concept of navigation graphs, where each screen or destination is represented by a composable function. The navigation graph defines the connections between destinations, and navigation actions can be triggered using predefined navigation methods."
    ),
    FlashCardViewData(
        question = "Explain the concept of recomposition in Jetpack Compose.",
        answer = "Recomposition is the process of automatically updating only the parts of the UI that have changed when the state variables of a Composable function are modified. Jetpack Compose tracks the dependencies between Composable functions and their state variables. When a state variable changes, only the affected Composable functions are recomposed, ensuring efficient UI updates."
    ),
    FlashCardViewData(
        question = "How can you handle user input and events in Jetpack Compose?",
        answer = "User input and events can be handled using event callbacks and state variables in Jetpack Compose. Composable functions can define event callbacks that are triggered when a user interacts with the UI. These callbacks can modify the state variables, leading to UI recomposition and updates."
    ),
    FlashCardViewData(
        question = "What is the purpose of ViewModel in Jetpack Compose?",
        answer = "ViewModel in Jetpack Compose is used for managing and preserving UI-related data across configuration changes. It provides a way to separate the UI logic from the UI components and allows sharing data between multiple Composable functions. ViewModels can be accessed using the viewModel or viewModel<>() functions."
    ),
    FlashCardViewData(
        question = "How does Jetpack Compose integrate with existing Android frameworks and libraries?",
        answer = "Jetpack Compose can be integrated with existing Android frameworks and libraries by using interoperability features. Compose provides compatibility libraries to bridge the gap between Compose and existing View-based UI components. Additionally, Compose supports the embedding of traditional View-based components within Composable functions using the AndroidView or ComposeView APIs."
    ),
    FlashCardViewData(
        question = "What are the key components of the Jetpack Compose architecture?",
        answer = "The key components of the Jetpack Compose architecture include Composable functions, state management, the Modifier system, navigation with the Navigation Compose library, ViewModel, and integration with existing Android frameworks."
    ),
    FlashCardViewData(
        question = "How do you perform animations in Jetpack Compose?",
        answer = "Animations in Jetpack Compose can be performed using the animate* family of functions. These functions allow you to animate changes to UI properties, such as size, position, opacity, and color. Jetpack Compose handles the animation updates and UI recomposition automatically."
    ),
    FlashCardViewData(
        question = "What is the purpose of ConstraintLayout in Jetpack Compose?",
        answer = "ConstraintLayout in Jetpack Compose is used to create complex and responsive layouts. It allows you to define constraints between UI elements, enabling flexible positioning and resizing based on the available space. ConstraintLayout helps in building dynamic and adaptive UIs."
    ),
    FlashCardViewData(
        question = "How do you handle theming and styling in Jetpack Compose?",
        answer = "Theming and styling in Jetpack Compose are handled using the MaterialTheme and @Composable functions. MaterialTheme provides a pre-defined set of styles and attributes, while the @Composable function allows for creating custom styles and applying them to UI elements."
    ),
    FlashCardViewData(
        question = "Explain the concept of side effects in Jetpack Compose.",
        answer = "Side effects in Jetpack Compose refer to operations that have additional effects beyond modifying the UI state. Examples include making network requests, accessing databases, or updating shared preferences. Side effects are handled using functions like LaunchedEffect or DisposableEffect, which allow you to perform these operations while ensuring proper lifecycle management."
    ),
    FlashCardViewData(
        question = "How can you handle asynchronous operations in Jetpack Compose?",
        answer = "Asynchronous operations in Jetpack Compose can be handled using Kotlin coroutines and the suspend function modifier. You can launch coroutines within Composable functions using LaunchedEffect or other coroutine builders. This allows you to perform asynchronous operations off the main thread and update the UI when the operation completes."
    ),
    FlashCardViewData(
        question = "What are Compose key events, and how are they used?",
        answer = "Compose key events allow you to handle keyboard events, such as key presses, in Jetpack Compose. You can use the onKeyEvent modifier to listen to key events and define the corresponding actions within Composable functions."
    ),
    FlashCardViewData(
        question = "How do you test UI components in Jetpack Compose?",
        answer = "Jetpack Compose provides a testing framework that allows you to write tests for UI components. You can use the @Composable test rule and the composeTestRule to interact with and assert the behavior of Composable functions and UI elements."
    ),
    FlashCardViewData(
        question = "What is the purpose of remember in Jetpack Compose, and how is it used?",
        answer = "The remember function in Jetpack Compose is used to create and store a value that survives recomposition. It allows you to preserve and reuse expensive computations or expensive objects, optimizing performance by avoiding unnecessary recomputations."
    ),
    FlashCardViewData(
        question = "What is the role of StateFlow and SharedFlow in Jetpack Compose?",
        answer = "StateFlow and SharedFlow are part of the Kotlin coroutines library and can be used in Jetpack Compose for managing state and asynchronous data streams. StateFlow represents a state value that can be observed for changes, while SharedFlow represents a hot data stream that can be collected by multiple collectors."
    ),
    FlashCardViewData(
        question = "How can you handle input validation in Jetpack Compose?",
        answer = "Input validation in Jetpack Compose can be handled by combining state management and event callbacks. You can use state variables to hold the input values and define validation logic within event callbacks or helper functions. Based on the validation results, you can update the UI to provide feedback to the user."
    ),
    FlashCardViewData(
        question = "Explain the concept of lazy composition in Jetpack Compose.",
        answer = "Lazy composition in Jetpack Compose allows you to defer the execution of expensive Composable functions until they are actually needed. It helps optimize performance by only recomposing and rendering UI components when they become visible or relevant to the current state."
    ),
    FlashCardViewData(
        question = "What are recomposition triggers, and how can you use them?",
        answer = "Recomposition triggers are used to manually trigger recomposition of specific parts of the UI. They allow you to control the timing of UI updates and ensure that only the necessary parts are recomposed. Recomposition triggers can be used in scenarios where you want to force an update, such as handling external events or user interactions."
    ),
    FlashCardViewData(
        question = "How can you handle keyboard input in Jetpack Compose?",
        answer = "Keyboard input in Jetpack Compose can be handled using the TextField component. It provides an editable text field that allows users to enter text and handles keyboard events, such as input changes or key presses. You can customize the behavior of the TextField using various parameters and modifiers."
    ),
    FlashCardViewData(
        question = "What is the role of CompositionLocal in Jetpack Compose?",
        answer = "CompositionLocal in Jetpack Compose is used to provide values that can be accessed by the Composable functions in the composition tree. It allows you to pass down values, such as themes, fonts, or other contextual information, without explicitly passing them through function parameters."
    ),
    FlashCardViewData(
        question = "How do you handle orientation changes in Jetpack Compose?",
        answer = "Orientation changes in Jetpack Compose are handled automatically by recomposing the UI based on the new configuration. Composable functions that define the UI layout and behavior will be recomposed with the updated configuration, allowing the UI to adapt to the new orientation."
    ),
    FlashCardViewData(
        question = "Explain the concept of accessibility support in Jetpack Compose.",
        answer = "Accessibility support in Jetpack Compose allows you to create UIs that are accessible to users with disabilities. Compose provides accessibility modifiers, such as semantics, screenReaderOnly, and clickable, to enhance the accessibility of UI elements. You can also use setContentDescription to provide meaningful descriptions for screen readers."
    ),
    FlashCardViewData(
        question = "How can you integrate Jetpack Compose with data binding?",
        answer = "Jetpack Compose can be integrated with data binding by using the observeAsState function. It allows you to observe LiveData objects from the data binding library and use them as state variables within Composable functions. This enables seamless integration between Compose and data binding in mixed projects."
    ),
    FlashCardViewData(
        question = "What are the best practices for performance optimization in Jetpack Compose?",
        answer = "Some best practices for performance optimization in Jetpack Compose include:\nMinimize unnecessary recompositions by using immutable state objects and avoiding excessive state changes.\nUse the remember function to cache expensive computations and avoid unnecessary recomputations.\nUse the key parameter to explicitly control the identity of Composable functions and optimize the diffing algorithm.\nUse LaunchedEffect and other coroutine-based APIs to perform asynchronous operations off the main thread and ensure smooth and responsive user interfaces in Jetpack Compose."
    ),
    FlashCardViewData(
        question = "What is Android Jetpack, and why is it important for Android development?",
        answer = "Android Jetpack is a set of libraries, tools, and architectural components designed to help developers create robust, maintainable, and high-quality Android apps. It simplifies common tasks such as managing UI components, handling data, and performing background operations.\n\nImportance:\n\nConsistency: Jetpack provides standardized and consistent APIs that simplify app development and maintenance.\nModern Architecture: It supports modern architectural patterns like MVVM and helps implement best practices with components such as LiveData, ViewModel, and Data Binding.\nBackward Compatibility: Many Jetpack libraries are designed to work with older versions of Android, providing backward compatibility and ensuring a wide range of device support.\nProductivity: Jetpack components reduce boilerplate code, allowing developers to focus more on building features and improving app performance."
    ),
    FlashCardViewData(
        question = "Explain the purpose and benefits of using ViewModel in Android Jetpack.",
        answer = "The ViewModel is part of the Jetpack Architecture Components and is designed to store and manage UI-related data in a lifecycle-conscious way.\n\nBenefits:\n\nLifecycle Awareness: ViewModel is lifecycle-aware, meaning it survives configuration changes like screen rotations. This helps prevent data loss and reduces the need for manual state management.\nSeparation of Concerns: It separates UI-related data from the Activity or Fragment, adhering to the MVVM architecture pattern. This results in cleaner and more manageable code.\nData Persistence: Data stored in ViewModel persists across configuration changes, such as screen rotations, improving the user experience by retaining state.\nEase of Testing: The ViewModel is easier to test in isolation since it doesn’t depend on the UI components directly."
    ),
    FlashCardViewData(
        question = "How does LiveData work with ViewModel, and what are its key benefits?",
        answer = "LiveData is an observable data holder class that is lifecycle-aware. It is often used in conjunction with ViewModel to manage UI-related data.\n\nKey Benefits:\n\nLifecycle Awareness: LiveData is lifecycle-aware and only sends updates to active observers. This prevents memory leaks and avoids the risk of updating UI elements when they are not visible.\nAutomatic Updates: It automatically updates the UI when the data changes, reducing the need for manual refreshes.\nConsistent Data Handling: It ensures that data changes are handled consistently across different lifecycle states of an Activity or Fragment.\nNo Memory Leaks: Since LiveData is lifecycle-aware, it helps prevent memory leaks by only notifying active observers."
    ),
    FlashCardViewData(
        question = "What is Room, and how does it simplify database management in Android apps?",
        answer = "Room is an abstraction layer over SQLite that provides a more convenient API for database access while leveraging SQLite’s full power.\n\nKey Features:\n\nData Access Objects (DAOs): Room uses DAOs to define methods for accessing the database, providing a type-safe API.\nEntity Classes: It allows you to define database tables using entity classes annotated with @Entity, simplifying schema management.\nAutomatic Schema Migration: Room supports schema migration by providing annotations and a migration API to handle schema changes gracefully.\nCompile-Time Checks: Room performs compile-time checks to ensure SQL queries are valid and match the expected return types, reducing runtime errors."
    ),
    FlashCardViewData(
        question = "How does the WorkManager library handle background tasks, and what are its key features?",
        answer = "WorkManager is a library for managing background tasks in a flexible and efficient way, especially when tasks need to be guaranteed to execute, even if the app or device restarts.\n\nKey Features:\n\nGuaranteed Execution: Ensures that background tasks are executed reliably, even in the case of device reboots or app restarts.\nFlexible Scheduling: Allows for tasks to be scheduled with constraints such as network availability, battery level, or charging status.\nWork Chaining: Supports chaining multiple work requests together, making it easier to handle complex workflows.\nBackward Compatibility: Works on API levels 14 and above, providing a unified API for managing background work across different Android versions."
    ),
    FlashCardViewData(
        question = "Explain the concept of Navigation Component in Jetpack and its benefits.",
        answer = "The Navigation Component is part of Jetpack’s Architecture Components and simplifies navigation within an app by providing a framework for managing fragment transactions and navigation patterns.\n\nBenefits:\n\nSimplified Navigation: It provides a declarative way to manage navigation and transitions between fragments or activities using a navigation graph.\nDeep Linking: Supports deep linking, enabling navigation to specific parts of the app from external sources or other apps.\nType-Safe Arguments: Allows for safe argument passing between destinations, reducing the risk of runtime errors.\nBack Stack Management: Manages the back stack automatically, ensuring consistent and predictable behavior when navigating back."
    ),
    FlashCardViewData(
        question = "What is Data Binding in Android Jetpack, and how does it improve UI development?",
        answer = "Data Binding is a library that allows you to bind UI components directly to data sources in your layout XML files, reducing boilerplate code and simplifying UI development.\n\nBenefits:\n\nReduced Boilerplate Code: Eliminates the need for findViewById() calls by binding views directly to data sources.\nDeclarative Syntax: Uses a declarative syntax in XML to bind data, which can make the codebase more readable and maintainable.\nTwo-Way Data Binding: Supports two-way data binding, allowing changes in the UI to update the underlying data and vice versa.\nIntegration with LiveData: Works seamlessly with LiveData and ViewModel to update the UI automatically when data changes."
    ),
    FlashCardViewData(
        question = "Describe the purpose of Hilt in Android and how it simplifies dependency injection.",
        answer = "Hilt is a dependency injection library for Android that simplifies the process of managing dependencies by integrating with the Dagger 2 library.\n\nKey Benefits:\n\nSimplified Setup: Provides a simpler and more streamlined setup compared to Dagger 2, with reduced boilerplate code.\nAutomatic Injection: Automatically injects dependencies into Android components like Activity, Fragment, and ViewModel, reducing manual injection code.\nScoped Dependencies: Supports different scopes such as @Singleton and @ActivityScoped, ensuring that dependencies are managed efficiently and consistently.\nIntegration with Jetpack: Works seamlessly with Jetpack libraries, providing a unified approach to dependency injection in Android apps."
    ),
    FlashCardViewData(
        question = "What is Paging 3, and how does it improve data loading in Android apps?",
        answer = "Paging 3 is part of Jetpack’s Paging library and is designed to load large data sets efficiently by paging through data in chunks.\n\nKey Features:\n\nAsynchronous Data Loading: Handles loading data in chunks asynchronously, improving app performance and responsiveness.\nIntegration with Room and LiveData: Integrates seamlessly with Room and LiveData, allowing for efficient paging through database queries.\nFlexible Paging Sources: Provides different types of paging sources, such as PagingSource for loading data from any source, including network APIs and databases.\nImproved Performance: Reduces memory consumption and processing time by loading only the data needed for the current page and loading more data as the user scrolls."
    ),
    FlashCardViewData(
        question = "How does the Jetpack Compose library change the way UI is built in Android?",
        answer = "Jetpack Compose is a modern UI toolkit for building native Android UIs using a declarative approach.\n\nKey Benefits:\n\nDeclarative Syntax: Allows you to describe UI components and their behavior using a declarative syntax, making it easier to build and update UIs.\nComposable Functions: Uses composable functions to create UI elements, which can be combined and reused to build complex UIs.\nState Management: Simplifies state management by automatically updating the UI when the state changes.\nUnified Approach: Provides a unified approach for building UIs across different screen sizes and device configurations."
    ),
    FlashCardViewData(
        question = "What is String Interpolation in Kotlin?",
        answer = "If you want to use some variable or perform some operation inside a string then String Interpolation can be used. You can use the $ sign to use some variable in the string or can perform some operation in between {} sign."
    ),
    FlashCardViewData(
        question = "What do you mean by destructuring in Kotlin?",
        answer = "Destructuring is a convenient way of extracting multiple values from data stored in (possibly nested) objects and Arrays. It can be used in locations that receive data (such as the left-hand side of an assignment). Sometimes it is convenient to destructure an object into a number of variables, for example val (name, age) = developer."
    ),
    FlashCardViewData(
        question = "What is a data class in Kotlin?",
        answer = "Data classes are those classes which are made just to store some data. In Kotlin, it is marked as data. The following is an example of the same:\n\n" +
                "data class Developer(val name: String, val age: Int)\n\n" +
                "When we mark a class as a data class, you don’t have to implement or create the following functions like we do in Java: hashCode(), equals(), toString(), copy(). The compiler automatically creates these internally, so it also leads to clean code. Although, there are few other requirements that data classes need to fulfill."
    ),
    FlashCardViewData(
        question = "What is the difference between the variable declaration with val and const?",
        answer = "Both the variables that are declared with val and const are immutable in nature. " +
                "However, the value of the const variable must be known at compile-time, whereas the value of the val variable can be assigned at runtime."
    ),
    FlashCardViewData(
        question = "Do we have a ternary operator in Kotlin just like Java?",
        answer = "No, we don't have a ternary operator in Kotlin, but you can use the functionality of the ternary operator by using if-else or the Elvis operator."
    ),
    FlashCardViewData(
        question = "When to use the lateinit keyword in Kotlin?",
        answer = "Lateinit is for late initialization.\n\n" +
                "Normally, properties declared as having a non-null type must be initialized in the constructor. " +
                "However, this is often inconvenient, as properties can be initialized through dependency injection or in the setup method of a unit test. " +
                "In these cases, you cannot supply a non-null initializer in the constructor, but you still want to avoid null checks when referencing the property inside the class body. " +
                "To handle this, you can mark the property with the lateinit modifier."
    ),
    FlashCardViewData(
        question = "What is the difference between lateinit and lazy in Kotlin?",
        answer = "Lazy can only be used for val properties, whereas lateinit can only be applied to var properties because it can’t be compiled to a final field, thus no immutability can be guaranteed. " +
                "If you want your property to be initialized from outside in a way probably unknown beforehand, use lateinit."
    ),
    FlashCardViewData(
        question = "What are companion objects in Kotlin?",
        answer = "In Kotlin, if you want to write a function or any member of the class that can be called without having an instance of the class, you can write it as a member of a companion object inside the class.\n\n" +
                "To create a companion object, you need to add the 'companion' keyword in front of the object declaration.\n\n" +
                "The following is an example of a companion object in Kotlin:\n\n" +
                "class ToBeCalled {\n" +
                "    companion object Test {\n" +
                "        fun callMe() = println(\"You are calling me :)\")\n" +
                "    }\n" +
                "}\n\n" +
                "fun main(args: Array<String>) {\n" +
                "    ToBeCalled.callMe()\n" +
                "}"
    ),
    FlashCardViewData(
        question = "What is the difference between FlatMap and Map in Kotlin?",
        answer = "FlatMap is used to combine all the items of lists into one list, while Map is used to transform a list based on certain conditions."
    ),
    FlashCardViewData(
        question = "What is the difference between List and Array types in Kotlin?",
        answer = "If you have a list of data that has a fixed size, then you can use an Array. " +
                "However, if the size of the list can vary, then you should use a mutable list."
    ),
    FlashCardViewData(
        question = "What are visibility modifiers in Kotlin?",
        answer = "A visibility modifier (or access specifier) defines the scope of something in a programming language. In Kotlin, we have four visibility modifiers:\n\n" +
                "1. **private**: Visible inside that particular class or file containing the declaration.\n" +
                "2. **protected**: Visible inside that particular class or file and also in subclasses.\n" +
                "3. **internal**: Visible everywhere in that particular module.\n" +
                "4. **public**: Visible to everyone.\n\n" +
                "Note: By default, the visibility modifier in Kotlin is public."
    ),
    FlashCardViewData(
        question = "How to create a Singleton class in Kotlin?",
        answer = "A singleton class is defined so that only one instance of the class can be created. It is used where we need a single instance, such as in logging or database connections.\n\n" +
                "To create a Singleton class in Kotlin, use the object keyword:\n\n" +
                "```kotlin\n" +
                "object AnySingletonClassName {\n" +
                "    // Properties and methods\n" +
                "}\n" +
                "```\n\n" +
                "Note: You can't use a constructor in an object, but you can use an init block."
    ),
    FlashCardViewData(
        question = "What are init blocks in Kotlin?",
        answer = "Init blocks are initializer blocks that are executed just after the execution of the primary constructor. A class can have one or more init blocks that will be executed in series. " +
                "If you want to perform some operations in the primary constructor, you need to use the init block."
    ),
    FlashCardViewData(
        question = "What are the types of constructors in Kotlin?",
        answer = "1. **Primary Constructor**: Defined in the class header; you can't perform operations in it like you can in Java's constructors.\n" +
                "2. **Secondary Constructor**: Declared inside the class body using the 'constructor' keyword. You must call the primary constructor explicitly from the secondary constructor. " +
                "Properties of the class can’t be declared inside the secondary constructor, and there can be more than one secondary constructor in Kotlin."
    ),
    FlashCardViewData(
        question = "Is there any relationship between primary and secondary constructors?",
        answer = "Yes, when using a secondary constructor, you need to call the primary constructor explicitly."
    ),
    FlashCardViewData(
        question = "What is the default type of argument used in a constructor?",
        answer = "By default, the type of arguments of a constructor is 'val'. You can change it to 'var' explicitly."
    ),
    FlashCardViewData(
        question = "What are Coroutines in Kotlin?",
        answer = "Coroutines are a framework to manage concurrency in a more performant and simple way. They provide lightweight threads built on top of the actual threading framework, taking advantage of the cooperative nature of functions."
    ),
    FlashCardViewData(
        question = "What is a suspend function in Kotlin Coroutines?",
        answer = "A suspend function is the building block of Coroutines in Kotlin. It is a function that can be started, paused, and resumed. " +
                "To use a suspend function, you need to use the 'suspend' keyword in your normal function definition."
    ),
    FlashCardViewData(
        question = "What is the difference between Launch and Async in Kotlin Coroutines?",
        answer = "The difference is that `launch {}` does not return anything, while `async {}` returns an instance of `Deferred<T>`, " +
                "which has an `await()` function that retrieves the result of the coroutine, similar to how `Future` works in Java with `future.get()`.\n\n" +
                "In other words:\n" +
                "- **launch**: fire and forget\n" +
                "- **async**: perform a task and return a result"
    ),
    FlashCardViewData(
        question = "How to choose between a switch and when in Kotlin?",
        answer = "In Kotlin, while switch-case statements are commonly used for handling multiple if-else conditions, Kotlin offers a more concise alternative: the `when` expression. " +
                "The `when` expression can be used in various ways:\n\n" +
                "- As an expression\n" +
                "- With arbitrary condition expressions\n" +
                "- Without an argument\n" +
                "- With multiple choices\n\n" +
                "### Example:\n" +
                "```kotlin\n" +
                "when (number) {\n" +
                "    1 -> println(\"One\")\n" +
                "    2, 3 -> println(\"Two or Three\")\n" +
                "    4 -> println(\"Four\")\n" +
                "    else -> println(\"Number is not between 1 and 4\")\n" +
                "}\n" +
                "```\n" +
                "In this example, `when` replaces the traditional switch-case, providing a clearer and more flexible way to handle multiple conditions."
    ),
    FlashCardViewData(
        question = "What is the open keyword in Kotlin used for?",
        answer = "In Kotlin, classes and functions are final by default, meaning they cannot be inherited or overridden. " +
                "To allow a class to be inherited or a function to be overridden, you need to use the `open` keyword before the class or function declaration.\n\n" +
                "### Example:\n" +
                "```kotlin\n" +
                "open class BaseClass {\n" +
                "    open fun display() {\n" +
                "        println(\"Base Class Display\")\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class DerivedClass : BaseClass() {\n" +
                "    override fun display() {\n" +
                "        println(\"Derived Class Display\")\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "fun main() {\n" +
                "    val derived = DerivedClass()\n" +
                "    derived.display() // Outputs: Derived Class Display\n" +
                "}\n" +
                "```\n" +
                "In this example, `BaseClass` is marked as `open`, allowing `DerivedClass` to inherit from it and override the `display` function."
    ),
    FlashCardViewData(
        question = "What are lambda expressions in Kotlin?",
        answer = "Lambda expressions are anonymous functions that can be treated as values. This means you can pass lambda expressions as arguments to functions, return them, or perform any other operations you would do with a normal object.\n\n" +
                "### Example:\n" +
                "```kotlin\n" +
                "val add: (Int, Int) -> Int = { a, b -> a + b }\n" +
                "val result = add(9, 10) // result will be 19\n" +
                "```\n" +
                "In this example, a lambda expression is used to define an addition operation, which can then be invoked like a regular function."
    ),
    FlashCardViewData(
        question = "What are higher-order functions in Kotlin?",
        answer = "A higher-order function is a function that either takes functions as parameters or returns a function.\n\n" +
                "### Example of a function taking another function as a parameter:\n" +
                "```kotlin\n" +
                "fun passMeFunction(abc: () -> Unit) {\n" +
                "    // Execute the function\n" +
                "    abc()\n" +
                "}\n" +
                "```\n" +
                "### Example of a function returning another function:\n" +
                "```kotlin\n" +
                "fun add(a: Int, b: Int): Int {\n" +
                "    return a + b\n" +
                "}\n" +
                "\n" +
                "fun returnMeAddFunction(): ((Int, Int) -> Int) {\n" +
                "    return ::add\n" +
                "}\n" +
                "```\n" +
                "### Calling the returned function:\n" +
                "```kotlin\n" +
                "val add = returnMeAddFunction()\n" +
                "val result = add(2, 2) // result will be 4\n" +
                "```\n" +
                "Higher-order functions enable functional programming capabilities in Kotlin."
    ),
    FlashCardViewData(
        question = "What are extension functions in Kotlin?",
        answer = "Extension functions allow you to add new methods or functionalities to an existing class without inheriting it. " +
                "They are like additional properties attached to the class. For example, you can create extension functions for views to manage visibility:\n\n" +
                "```kotlin\n" +
                "fun View.show() {\n" +
                "    this.visibility = View.VISIBLE\n" +
                "}\n" +
                "\n" +
                "fun View.hide() {\n" +
                "    this.visibility = View.GONE\n" +
                "}\n" +
                "```\n" +
                "You can then use these functions like this:\n" +
                "```kotlin\n" +
                "toolbar.hide()\n" +
                "```\n" +
                "This allows for cleaner and more readable code."
    ),
    FlashCardViewData(
        question = "What is an infix function in Kotlin?",
        answer = "An infix function allows you to call a function without using any brackets or parentheses. " +
                "To define an infix function, you need to use the `infix` keyword.\n\n" +
                "Example:\n" +
                "```kotlin\n" +
                "class Operations {\n" +
                "    var x = 10\n" +
                "    infix fun minus(num: Int) {\n" +
                "        this.x = this.x - num\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "fun main() {\n" +
                "    val opr = Operations()\n" +
                "    opr minus 8\n" +
                "    print(opr.x) // Outputs: 2\n" +
                "}\n" +
                "```\n" +
                "In this example, the `minus` function is called in an infix style."
    ),
    FlashCardViewData(
        question = "What is an inline function in Kotlin?",
        answer = "An inline function instructs the compiler to insert the complete body of the function wherever that function is used in the code. " +
                "To use an inline function, simply add the `inline` keyword at the beginning of the function declaration.\n\n" +
                "Example:\n" +
                "```kotlin\n" +
                "inline fun exampleFunction() {\n" +
                "    println(\"This is an inline function\")\n" +
                "}\n" +
                "```\n" +
                "In this example, `exampleFunction` will be inlined at each call site."
    ),
    FlashCardViewData(
        question = "What is noinline in Kotlin?",
        answer = "In Kotlin, `noinline` is used when defining an inline function. If you want to pass a lambda function to an inline function but don't want all lambdas to be inlined, " +
                "you can explicitly tell the compiler which lambda should not be inlined using the `noinline` keyword.\n\n" +
                "Example:\n" +
                "```kotlin\n" +
                "inline fun doSomethingElse(abc: () -> Unit, noinline xyz: () -> Unit) {\n" +
                "    abc()\n" +
                "    xyz()\n" +
                "}\n" +
                "```\n" +
                "In this example, the lambda `abc` will be inlined, while `xyz` will not be inlined."
    ),
    FlashCardViewData(
        question = "What are reified types in Kotlin?",
        answer = "Reified types allow you to access the type of a generic class at runtime. When using generics to pass a class as a parameter to a function, " +
                "you need to use the `reified` keyword to access the type.\n\n" +
                "Example:\n" +
                "```kotlin\n" +
                "inline fun <reified T> genericsExample(value: T) {\n" +
                "    println(value)\n" +
                "    println(\"Type of T: \$T::class.java\")\n" +
                "}\n" +
                "\n" +
                "fun main() {\n" +
                "    genericsExample<String>(\"Learning Generics!\")\n" +
                "    genericsExample<Int>(100)\n" +
                "}\n" +
                "```\n" +
                "In this example, `genericsExample` prints the value and the type of `T`."
    ),
    FlashCardViewData(
        question = "What is operator overloading in Kotlin?",
        answer = "In Kotlin, operator overloading allows us to use the same operator to perform various tasks. " +
                "To achieve this, we need to provide a member function or an extension function with a fixed name and the `operator` keyword before the function name. " +
                "When using an operator like `+`, it is converted to a corresponding function call (e.g., `num1.plus(num2)`).\n\n" +
                "Example:\n" +
                "```kotlin\n" +
                "fun main() {\n" +
                "    val bluePen = Pen(inkColor = \"Blue\")\n" +
                "    bluePen.showInkColor()\n" +
                "\n" +
                "    val blackPen = Pen(inkColor = \"Black\")\n" +
                "    blackPen.showInkColor()\n" +
                "\n" +
                "    val blueBlackPen = bluePen + blackPen\n" +
                "    blueBlackPen.showInkColor()\n" +
                "}\n" +
                "\n" +
                "operator fun Pen.plus(otherPen: Pen): Pen {\n" +
                "    val ink = \"inkColor, otherPen.inkColor\"\n" +
                "    return Pen(inkColor = ink)\n" +
                "}\n" +
                "\n" +
                "data class Pen(val inkColor: String) {\n" +
                "    fun showInkColor() { println(inkColor) }\n" +
                "}\n" +
                "```\n" +
                "In this example, two pens can be combined using the `+` operator."
    ),
    FlashCardViewData(
        question = "What are Pair and Triple in Kotlin?",
        answer = "Pair and Triple are used to return two and three values, respectively, from a function. The returned values can be of the same data type or different.\n\n" +
                "Example of Pair:\n" +
                "```kotlin\n" +
                "val pair = Pair(\"My Age: \", 25)\n" +
                "print(pair.first + pair.second)\n" +
                "```\n" +
                "In this example, the Pair contains a string and an integer."
    ),
    FlashCardViewData(
        question = "What are labels in Kotlin?",
        answer = "Any expression written in Kotlin is called a label. For example, if you have a for-loop, you can name that for-loop expression as a label and use the label name for the for-loop.\n\n" +
                "You can create a label by using an identifier followed by the @ sign. For example: `name@`, `loop@`, `xyz@`, etc.\n\n" +
                "Example of a label:\n\n" +
                "```kotlin\n" +
                "loop@ for (i in 1..10) {\n" +
                "    // some code goes here\n" +
                "}\n" +
                "```\n" +
                "In this example, the name of the for-loop is 'loop'."
    ),
    FlashCardViewData(
        question = "What are the benefits of using a Sealed Class over Enum?",
        answer = "Sealed classes provide the flexibility of having different types of subclasses and the ability to contain state. " +
                "An important point to note is that the subclasses extending the sealed class must be either nested classes of the sealed class or declared in the same file as the sealed class."
    ),


)


