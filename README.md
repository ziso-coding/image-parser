# image-parser
This is a console application for turning an image into a matrix with values of 0 or 1, in which the value of 1 means the presence of color in a particular pixel.

## Usage
- build project image-parser.jar
- write to command line (java -jar <path to image-parser.jar> <path to source image>)
- open file output.kt
### or
- replace the line ```val file = File(arg.first())``` by ```val file = File(<path to image>)```
- run function main
- open file output.kt 

## Source image
![Alt-source](https://github.com/ziso-coding/image-parser/blob/master/resources/input_image.png "source")


## Result
![Alt-result](https://github.com/ziso-coding/image-parser/blob/master/resources/result.jpg "result")
