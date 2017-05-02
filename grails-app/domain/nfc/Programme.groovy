package nfc

class Programme {
    Integer id
    Date dateCreated
    String progDesc

    static hasMany=[contenu:Contenu]

    static fetchMode = [contenu: 'join']

    static constraints = {
        progDesc(blank:false, nullable:false, length:100)
    }

    static mapping = {
        id generator: 'increment'
        //contenu fetch: 'join'
        contenu cascade: 'all-delete-orphan'
        sort dateCreated: 'desc'
    }
}
