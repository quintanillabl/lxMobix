package com.luxsoft.lx.core

class CoreTagLib {

    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]x
  
    static namespace="lx"

    def deleteButton={ attrs->
    	def action=attrs.action?:'delete'
    	def controller=attrs.controller?:controllerName
    	def label=attrs.label?:'Eliminar'
    	def bean=attrs.bean
    	if(!bean)
    		throw new IllegalArgumentException("Tag lx:deleteButton Debe pasar el  bean a eliminar")
    	def model=[action:actionName,controller:controller]
    	//out<<render(template:"/common/deleteDialog")
    	//out<<"<a href='#deleteDialog' class="btn btn-danger"><i class='fa fa-trash'></i> Eliminar</a>"
    	//out << "<a href="#deleteDialog" class="btn btn-danger"><i class="fa fa-trash"></i> Eliminar</a>"
    	out << render(template:"/common/deleteButton" ,model:[it:bean])
    	//out << 'HOLA'

    }
}
