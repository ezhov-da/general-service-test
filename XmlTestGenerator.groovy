//-Xmx4096m
import javax.xml.stream.XMLOutputFactory

def fileName = "test.xml"

FileOutputStream fileOutputStream = null;
try {
    fileOutputStream = new FileOutputStream(new File(fileName))
    def writer = XMLOutputFactory.newFactory().createXMLStreamWriter(fileOutputStream);
    writer.writeStartDocument()
    writer.writeStartElement("root")
    writer.writeStartElement("data")
    writer.writeStartElement("name")
    writer.writeCharacters("TEST")
    writer.writeEndElement() //name
    writer.writeStartElement("parts")
    System.out.print("[" + (" " * 10) + "] 00%")
    for (int i = 0; i < 1_000_000; i++) {
        writer.writeStartElement("part")
        writer.writeCharacters(new Random().nextInt(1000000000) + "")
        writer.writeEndElement() //part

        if ((i % 100_000) == 0) {
            def part = i / 100_000
            print "\r"
            print """[${'#' * part}${" " * (10 - (part + 1))}] ${part}0%"""
        }
    }
    println()
    writer.writeEndElement() //parts
    writer.writeEndElement() //data
    writer.writeEndElement() //root
    writer.writeEndDocument()
    println "complete"
} finally {
    if (fileOutputStream != null) {
        fileOutputStream.close()
    }
}


