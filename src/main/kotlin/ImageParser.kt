import java.awt.image.DataBufferByte
import java.io.File
import java.io.FileWriter
import javax.imageio.ImageIO

fun main(vararg arg: String) {
    val file = File(arg.first())
    val bufferedImage = ImageIO.read(file)
    val raster = bufferedImage.raster
    val data = raster.dataBuffer as DataBufferByte
    val pixels = data.data
    val width = raster.width
    val pixelLength = 4
    var pixel = 0
    var row = 0
    var col = 0
    var nestedList = mutableListOf<Int>()
    val mainList = mutableListOf<List<Int>>()
    while (pixel + 3 < pixels.size) {
        var argb = 0
        argb += pixels[pixel].toInt() and 0xff shl 24 // alpha
        argb += pixels[pixel + 1].toInt() and 0xff // blue
        argb += pixels[pixel + 2].toInt() and 0xff shl 8 // green
        argb += pixels[pixel + 3].toInt() and 0xff shl 16 // red
        if (argb != 0) {
            nestedList.add(1)
        } else {
            nestedList.add(0)
        }
        col++
        if (col == width) {
            mainList.add(nestedList)
            nestedList = mutableListOf()
            col = 0
            row++
        }
        pixel += pixelLength
    }

    mainList.reverse()
    val str = buildStr(mainList)
    val outFile = File("output.kt")
    val writer = FileWriter(outFile)
    writer.write(str)
    writer.close()
}

private fun buildStr(listPixelsRow: List<List<Int>>): String {
    var str = ""
    str += "private fun getMultipleArray(): List<List<Byte>> {" + '\n'
    str += "return listOf (" + '\n'
    listPixelsRow.forEachIndexed { index, list ->
        str += if (index != listPixelsRow.lastIndex) {
            "listOf (" + list.joinToString(",") + ")," + '\n'
        } else {
            "listOf (" + list.joinToString(",") + ")" + '\n'
        }
    }
    str += ")" + '\n'
    str += "}" + '\n'
    return str
}