package me.alrxz.swords.Utils;

import com.google.inject.Inject;
import me.alrxz.swords.Swords;
import org.jetbrains.annotations.NotNull;

public interface RecipeInitializer {
    @Inject
    void initRecipe(final @NotNull Swords swords);
}
