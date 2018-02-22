# 2 The Easy Animator Application

We will build this application progressively using the classic Model-View-Controller architecture.
In this assignment we will focus on the model of this application. There is no starter code for this
assignment: you must use the above description and broad ideas to design the interface and
implementation of the model. You are deliberately not being told about the fine details of what the
controller and views will need or do. You are also not being told about how exactly the application
will receive a description from the user (it would not be relevant to the model).

Here are some aspects to think about:

## What does the model represent?
- The model should be able to support various kinds of 2D shapes, although currently we have
described only rectangles and ovals.
- The model should support adding various kinds of animations to shapes, such as moving, changing
color, changing shape, etc.
- Remember to think from not only the implementor’s perspective (the person that is implementing the
model) but also the client perspective (the person or class that is using the model).

## 2.1 How is the animation seen?
One way the application may show the animation is by actually playing it, similar to the moving
images above. Another way would be to produce a text description of the animation. Here is what a
description might look like:

```
Shapes:
Name: R
Type: rectangle
Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
Appears at t=1
Disappears at t=100

Name: C
Type: oval
Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
Appears at t=6
Disappears at t=100

shape.IShape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
shape.IShape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70
shape.IShape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80
shape.IShape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
shape.IShape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100
```

In short, it first describes the shapes that are part of the animation and their details. Next it
describes how these shapes will move as the animation proceeds from start to finish. You may think
of this output as a “read-back” of the animation, perhaps for devices that cannot show the
animation, or for users who are visually impaired who have screen readers.