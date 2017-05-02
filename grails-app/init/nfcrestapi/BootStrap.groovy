package nfcrestapi

import grails.converters.JSON
import nfc.Contenu
import nfc.Programme

class BootStrap {
    // marshaller is simply a closure taking a domain class and returning a map
    def programmeMarshaller = { Programme prog ->
        return [
                id: prog.id,
                dateCreated: prog.dateCreated,
                progDesc: prog.progDesc,
                contenu: [
                        id: prog.contenu.id,
                        cType: prog.contenu.cType,
                        cTitre: prog.contenu.cTitre,
                        cDesc: prog.contenu.cDesc,
                        cOrdre: prog.contenu.cOrdre
                ]
        ]
    }

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Programme,programmeMarshaller)
    }
    def destroy = {
    }
}
