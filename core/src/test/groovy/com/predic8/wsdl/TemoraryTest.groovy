/* Copyright 2012 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.wsdl

import groovy.xml.MarkupBuilder

import com.predic8.wsdl.creator.WSDLCreator
import com.predic8.wsdl.creator.WSDLCreatorContext
import com.predic8.xml.util.*

/**
 * This is a template test class to parse and analyze temporary documents.
 */

class TemoraryTest extends GroovyTestCase {
	

  def wsdl

  void setUp(){
//	def parser = new WSDLParser()
//    wsdl = parser.parse(new WSDLParserContext(input:"C:/temp/test.wsdl"))
//    def parser = new WSDLParser(resourceResolver: new ClasspathResolver())
//    wsdl = parser.parse(new WSDLParserContext(input:"/RPCStyle.wsdl"))
//    wsdl = parser.parse(new WSDLParserContext(input:"/BLZService.wsdl"))
  }
  
  void testOutput(){
//	  println wsdl.getAsString()
//		def strWriter = new StringWriter()
//		def creator = new WSDLCreator(builder : new MarkupBuilder(strWriter))
//		creator.createDefinitions(wsdl, new WSDLCreatorContext())
//		println strWriter
  }
}

