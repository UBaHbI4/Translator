package softing.ubah4ukdev.screenfavourite

import softing.ubah4ukdev.domain.repository.IRepositoryLocal
import softing.ubah4ukdev.domain.storage.entity.WordFavourite

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.25
 *
 *   v1.0
 */
class FavouriteInteractor(
    val repositoryLocal: IRepositoryLocal
) : IFavouriteInteractor<List<WordFavourite>> {

    override suspend fun getData(): List<WordFavourite> =
        repositoryLocal
            .fetchFavourite()

    override suspend fun clearFavourite(): Int =
        repositoryLocal
            .clearFavourite()
}