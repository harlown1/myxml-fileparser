# 'MyXML' File Parser:
The goal of this project stems from my curiousity in how to parse a text file that conforms to some language definition.  After taking a class about programming languages and writing simple iterpretters for very small languages I figured a good next step is learning on my own how to parse a file into an Abstract Syntax Tree that I can work with later.  I decided a simple yet useful file type that I could begin with would be XML.  I am not trying to 100% conform to the language definition of XML at the time being, rather I am trying to be able to parse some subset of valid XML files.  
The API that I want to make will be modeled after the DOM used in the browser and with 
## Project Goals
- Parse the file into a Java Object that can be read.
- Create/write new MyXML files through the API.
- Be able to edit/change/rewrite the file.
- Learn to do a full build of a Java module that I can use in another project.
## MyXML language definition:
- A MyXML file is a single root node with any number of children nodes of that root, as well as any header/meta tags.
- A node consists of:
    - A name for the node
    - An arbitrary number of attributes that define the node
    - An optional string body of the information contained in the node.
    - Any number of children nodes.
- A well formed file is as follows:
    - A node is opened with a 'tag' which looks like one of the following: 
        - &lt;nodeName&gt;
        - &lt;nodeName attributes&gt;
    - A node's attributes are contained in the opening tag after the nodeName:
        - Each attribute is of the form:  attributeName="someValue"
        - An attribute name cannot contain spaces and is not quoted.
        - An attribute value can contain spaces and IS quoted.
        - Each attribute is separated by spaces from each other.
    - A node is closed with a 'tag' with a matching name as the opening tag which looks like:
        - &lt;/nodeName&gt;
    - All text between the opened and closed tag is part of the body of the tag.
    - All nodes that are between an opened and closed tag of a node are children of that node.
    - All the children nodes must be closed before the parents tag is closed.
    - A header tag is of the form:
        - &lt;?headerName attributes&gt;
        - Attributes are of the same form as in an opening tag.
        - Header tags just supply information about the document.
