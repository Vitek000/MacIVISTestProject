package fignya;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Viktor Aleksandrov on 30/08/16.
 */
class Test20160906 {


//    public
//     class ReadAndPrintXMLFileWithStAX {

        public static void main(String argv[]) throws Exception {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream("/Users/demo/Downloads/READY/34KL2016.xml");
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
            streamReader.nextTag(); // Advance to "book" element
            streamReader.nextTag(); // Advance to "person" element

            int persons = 0;
            int recordCount= 0;
            int dataField020Count= 0;
            while (streamReader.hasNext()) {



                boolean inRecord = false;
                if(streamReader.isStartElement() && streamReader.getLocalName().equals("record")) {
                    inRecord = true;

                    recordCount++;
                }

                if(streamReader.isEndElement() && streamReader.getLocalName().equals("record")) {
                    inRecord = false;

                }


               // if(inRecord && )






                    streamReader.next();



//                if (streamReader.isStartElement()) {
//
//
//
//                    //System.out.println(streamReader.getLocalName());
//
//
//                    if(streamReader.getLocalName().equals("record")) {
//                        recordCount++;
//                    }
//
//                    if(streamReader.getLocalName().equals("datafield") && streamReader.getAttributeValue(0).equals("020")) {
//                        dataField020Count++;
//                    }
//
//
//                    if(recordCount != dataField020Count && recordCount - 1 != dataField020Count) {
//                        //System.out.println(recordCount + " |||| " + dataField020Count);
//                        if(streamReader.getLocalName().equals("leader")) {
//                            System.out.println(streamReader.getElementText());
//                        }
//                    }
//
//
//                }
//
////                if (streamReader.isStartElement()) {
////                    switch (streamReader.getLocalName()) {
////                        case "first": {
////                            System.out.print("First Name : ");
////                            System.out.println(streamReader.getElementText());
////                            break;
////                        }
////                        case "last": {
////                            System.out.print("Last Name : ");
////                            System.out.println(streamReader.getElementText());
////                            break;
////                        }
////                        case "age": {
////                            System.out.print("Age : ");
////                            System.out.println(streamReader.getElementText());
////                            break;
////                        }
////                        case "person" : {
////                            persons ++;
////                        }
////                    }
////                }
//                streamReader.next();
            }
            System.out.print(persons);
            System.out.println(" persons");


            System.out.println("recordCount=" + recordCount);
            System.out.println("dataField020Count=" + dataField020Count);

        }

    }





