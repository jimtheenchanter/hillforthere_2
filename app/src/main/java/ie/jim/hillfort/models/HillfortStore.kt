package ie.jim.hillfort

import ie.jim.hillfort.models.HillfortModel

interface HillfortStore {
    fun findAll(): List<HillfortModel>
    fun findById(id:Long) : HillfortModel?
    fun create(hillfort: HillfortModel)
    fun update(hillfort: HillfortModel)
    fun delete(hillfort: HillfortModel)
}