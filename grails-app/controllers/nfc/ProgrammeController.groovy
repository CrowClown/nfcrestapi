package nfc

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProgrammeController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Programme.list(params), model:[programmeCount: Programme.count()]
    }

    def show(Programme programme) {
        respond programme
    }

    @Transactional
    def save(Programme programme) {
        if (programme == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (programme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond programme.errors, view:'create'
            return
        }

        programme.save flush:true

        respond programme, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Programme programme) {
        if (programme == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (programme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond programme.errors, view:'edit'
            return
        }

        programme.save flush:true

        respond programme, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Programme programme) {

        if (programme == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        programme.delete flush:true

        render status: NO_CONTENT
    }
}
