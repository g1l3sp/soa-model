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

package com.predic8.schema.diff

import com.predic8.soamodel.*
import org.apache.commons.logging.*

class ElementDiffGenerator extends UnitDiffGenerator {

  private Log log = LogFactory.getLog(this.class)
  
  def removed = {new Difference(description:"Element removed.", type: 'element', breaks: true, safe:false)}

  def added = { new Difference(description:"Element added.", type: 'element', breaks: true, safe:false)}

  def changed = { diffs ->
    new Difference(description:"Element ${a.name} has changed:" , type: 'element' ,  diffs : diffs, a: a, b:b)
  }

  protected getTypeAndName() {
     "element ${a.name}"
  }

  List<Difference> compareUnit(){
    log.debug("compareElement")
    def lDiffs = []
    lDiffs.addAll(compareAnnotation())
    lDiffs.addAll(compareType())
    lDiffs.addAll(compareMinMaxOccurs())
//    lDiffs.addAll(compareEmbeddedType())

    lDiffs
  }

  protected compareAnnotation(){
    a.annotation?.compare(generator, b.annotation) ?: []
  }


  protected compareType(){
    if(a.embeddedType && b.embeddedType) return compareEmbeddedType()
    if(a.embeddedType && b.type) return [new Difference(description:"The type of element '${a.name}' has changed from embedded to stand-alone.", type: 'element', safe: false)]
    if(a.type && b.embeddedType) return [new Difference(description:"The type of element '${a.name}' has changed from stand-alone to embedded.", type: 'element', safe: false)]
    if(a.type != b.type) return [new Difference(description:"The type of element '${a.name}' has changed from ${a.schema.getPrefix(a.type.namespaceURI)}:${a.type.localPart} to ${a.schema.getPrefix(b.type.namespaceURI)}:${b.type.localPart}.", type: 'element', breaks:true)]
    []
  }

  protected compareMinMaxOccurs(eType = 'element'){
		def lDiffs = []
		if(a.minOccurs != b.minOccurs){
			lDiffs << new Difference(description:"The attribute minOccurs of $eType ${a.name ? a.name+' ' : ''}has changed from ${a.minOccurs} to ${b.minOccurs}.", type: eType, safe:  a.minOccurs >= b.minOccurs, breaks:  a.minOccurs < b.minOccurs)
		}
		if(a.maxOccurs != b.maxOccurs){
			lDiffs << new Difference(description:"The attribute maxOccurs of $eType ${a.name ? a.name+' ' : ''}has changed from ${a.maxOccurs} to ${b.maxOccurs}.", type: eType, safe:  a.maxOccurs <= b.maxOccurs, breaks:  a.maxOccurs > b.maxOccurs)
		}
    lDiffs
  }

  private compareEmbeddedType(){
    a.embeddedType?.compare(generator, b.embeddedType) ?: []
  }

}

