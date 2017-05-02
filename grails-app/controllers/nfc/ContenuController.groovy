package nfc

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContenuController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contenu.list(params), model:[contenuCount: Contenu.count()]
    }

    def show(Contenu contenu) {
        respond contenu
    }

    @Transactional
    def save(Contenu contenu) {
        if (contenu == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (contenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contenu.errors, view:'create'
            return
        }

        contenu.save flush:true

        respond contenu, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Contenu contenu) {
        if (contenu == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (contenu.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contenu.errors, view:'edit'
            return
        }

        contenu.save flush:true

        respond contenu, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Contenu contenu) {

        if (contenu == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        contenu.delete flush:true

        render status: NO_CONTENT
    }
}
