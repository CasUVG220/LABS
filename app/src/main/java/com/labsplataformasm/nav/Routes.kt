package com.labsplataformasm.nav

import kotlinx.serialization.Serializable

@Serializable object Login
@Serializable object Characters
@Serializable data class CharacterDetail(val id: Int)
