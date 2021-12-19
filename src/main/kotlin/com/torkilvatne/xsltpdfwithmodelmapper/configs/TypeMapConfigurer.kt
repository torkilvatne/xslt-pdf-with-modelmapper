package com.torkilvatne.xsltpdfwithmodelmapper.configs


import org.modelmapper.ModelMapper
import org.modelmapper.TypeMap
import org.modelmapper.config.Configuration
import org.modelmapper.internal.typetools.TypeResolver

abstract class TypeMapConfigurer<S, D> {
    private val typeMapName: String?
        get() = null

    private val configuration: Configuration?
        get() = null

    abstract fun configure(typeMap: TypeMap<S, D>)
    fun configureImpl(mapper: ModelMapper) {
        val typeArguments: Array<Class<*>> = TypeResolver.resolveRawArguments(TypeMapConfigurer::class.java, javaClass)
        val typeMapName = typeMapName
        val configuration: Configuration? = configuration
        if (typeMapName == null && configuration == null) {
            configure(mapper.createTypeMap(typeArguments[0] as Class<S>, typeArguments[1] as Class<D>))
        } else if (typeMapName == null) {
            configure(mapper.createTypeMap(typeArguments[0] as Class<S>, typeArguments[1] as Class<D>, configuration))
        } else if (configuration == null) {
            configure(mapper.createTypeMap(typeArguments[0] as Class<S>, typeArguments[1] as Class<D>, typeMapName))
        } else {
            configure(
                mapper.createTypeMap(
                    typeArguments[0] as Class<S>,
                    typeArguments[1] as Class<D>,
                    typeMapName,
                    configuration
                )
            )
        }
    }
}