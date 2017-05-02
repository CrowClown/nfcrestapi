package nfc

class Contenu {
    Integer id
    String cType
    String cTitre
    String cDesc
    Integer cOrdre

    static  belongsTo=[programme:Programme]

    static constraints = {
        cType(blank:false, nullable:false, inList: ['Chant','Lecture','Participants'], length:20)
        cTitre(blank:false, nullable:false, length:50)
        cDesc(blank:false, nullable:false)
        cOrdre(nullable:false)
    }

    static mapping = {
        id generator: 'increment'
        cDesc type: 'text'
        sort cOrdre: 'asc'
        //programme column: 'prog_desc'
    }
}
