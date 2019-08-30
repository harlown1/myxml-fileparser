# 'MyXML' File Parser:
The goal of this project stems from my curiousity in how to parse a text file that conforms to some language definition.  After taking a class about programming languages and writing simple iterpretters for very small languages I figured a good next step is learning on my own how to parse a file into an Abstract Syntax Tree that I can work with later.  I decided a simple yet useful file type that I could begin with would be XML.  I am not trying to 100% conform to the language definition of XML at the time being, rather I am trying to be able to parse some subset of valid XML files.  
The API that I want to make will be modeled after the DOM used in the browser and with 
## Project Goals
- Parse the file into a Java Object that can be read.
- Create/write new MyXML files through the API.
- Be able to edit/change/rewrite the file.
- Learn to do a full build of a Java module that I can use in another project.
## MyXML language definition:
- A MyXML file is a single base root node named 'document' with any number of children nodes.
- A node consists of:
    - A name for the node
    - An arbitrary number of attributes that define the node
    - An optional string body of the information contained in the node.
    - Any number of children nodes.