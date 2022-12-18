package com.alexc.dogbreeds.common.utils

import com.alexc.dogbreeds.data.local.BreedEntity
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.domain.models.Height
import com.alexc.dogbreeds.domain.models.Image
import com.alexc.dogbreeds.domain.models.Weight

fun BreedEntity.toBreed(): Breed = Breed(
    id = id ?: "",
    name = name,
    breedGroup = breedGroup,
    lifeSpan = lifeSpan,
    origin = origin,
    weight = Weight(metric = weight),
    height = Height(metric = height),
    image = Image(imageUrl = imageUrl),
    imageId = imageId
)


fun Breed.toBreedEntity(): BreedEntity = BreedEntity(
    id = id ?: "",
    name = name,
    breedGroup = breedGroup,
    lifeSpan = lifeSpan,
    origin = origin,
    weight = weight.metric,
    height = height.metric,
    imageUrl = image.imageUrl,
    imageId = imageId
)
