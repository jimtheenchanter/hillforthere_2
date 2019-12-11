package ie.jim.hillfort.models

interface HillfortStore {
    fun findAll(): List<HillfortModel>
    fun findById(id:Long) : HillfortModel?
    fun create(hillfort: HillfortModel)
    fun update(hillfort: HillfortModel)
    fun delete(hillfort: HillfortModel)
}