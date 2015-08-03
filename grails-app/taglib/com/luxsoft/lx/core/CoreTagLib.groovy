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
        out << render(template:"/common/buttons/deleteButton" ,model:[it:bean])
        //out << 'HOLA'
    }

    def printButton={ attrs->
        def action=attrs.action?:'print'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Imprimir'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label]
        out << render(template:"/common/buttons/printButton" ,model:model)
        
    }

    def refreshButton={ attrs->
        def action=attrs.action?:'index'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Refrescar'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label]
        out << render(template:"/common/buttons/refreshButton" ,model:model)
    }

    def createButton={ attrs->
        def action=attrs.action?:'create'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Nuevo'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label]
        out << render(template:"/common/buttons/createButton" ,model:model)
    }

    def searchButton={ attrs->
        def action=attrs.action?:'search'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Busqueda'
        def title=attrs.title?:'Busqueda avanzada'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label,title:title]
        out << render(template:"/common/buttons/searchButton" ,model:model)
    }

    def editButton={ attrs ->
        def action=attrs.action?:'edit'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Editar'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label]
        out << render(template:"/common/buttons/editButton" ,model:model)

    }

    def backButton={ attrs ->
        def action=attrs.action?:'index'
        def controller=attrs.controller?:controllerName
        def label=attrs.label?:'Regresar'
        def id=attrs.id
        def model=[action:action,controller:controller,id:id,label:label]
        out << render(template:"/common/buttons/backButton" ,model:model)
    }
}
