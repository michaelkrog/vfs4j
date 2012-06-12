vfs4j
=====

Virtual File System for Java with a logical interface using "Directory" and "File" - both extending a "Node" - instead of everything just being a File.


The API is very simple and logic. The following is an example of use:

```Java
//Create a new filesystem based on the Native implementation in the directory /home/myuser
//The filesystem will have its root there.
FileSystem fs = new NativeFileSystem("/home/myuser");

Directory root = fs.getRoot();
List<File> childrenInRoot = root.getChildren();
for(Node child:childrenInRoot){
   
   System.out.print(dir.getName());
   
   if(child.isDirectory()){
      Directory dir = (Directory)child;
      System.out.println(" is a directory.");
   }
   else{
      File file = (File)child;
      ystem.out.println(" is a file.");
   }
}
```