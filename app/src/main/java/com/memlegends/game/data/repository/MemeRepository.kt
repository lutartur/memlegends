package com.memlegends.game.data.repository

import com.memlegends.game.data.model.Meme
import com.memlegends.game.data.model.MemeRarity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemeRepository @Inject constructor() {

    private val allMemes: List<Meme> = listOf(
        Meme(id = "woman_yelling_at_cat", name = "Woman Yelling at Cat", imageUrl = "file:///android_asset/memes/woman_yelling_at_cat.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "doge", name = "Doge", imageUrl = "file:///android_asset/memes/doge.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "shaking_chihuahua", name = "Shaking Chihuahua", imageUrl = "file:///android_asset/memes/no_take_only_throw.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "sad_pepe", name = "Sad Pepe", imageUrl = "file:///android_asset/memes/feels_bad_man.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "happy_pepe", name = "Happy Pepe", imageUrl = "file:///android_asset/memes/feels_good.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "this_is_a_fiasco_bro", name = "This is a fiasco, bro", imageUrl = "file:///android_asset/memes/michael_scott_no_god_no.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "didnt_understand_anything_but_its_interesting", name = "Didn't understand anything but it's interesting", imageUrl = "file:///android_asset/memes/futurama_fry.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "mom_told_me_to_take_out_the_trash", name = "Mom told me to take out the trash", imageUrl = "file:///android_asset/memes/we_have_food_at_home.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "just_sitting_here_minding_my_business", name = "Just sitting here minding my business", imageUrl = "file:///android_asset/memes/monkey_puppet.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "wideeyed_cat", name = "Wide-Eyed Cat", imageUrl = "file:///android_asset/memes/persian_cat_room_guardian.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "sad_okay_guy", name = "Sad Okay Guy", imageUrl = "file:///android_asset/memes/forever_alone.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "deadline_is_tomorrow", name = "Deadline is tomorrow", imageUrl = "file:///android_asset/memes/disaster_girl.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "expectation_vs_reality", name = "Expectation vs Reality", imageUrl = "file:///android_asset/memes/expectation_vs_reality.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "yeah_yeah_guess_ill_go_then", name = "Yeah yeah… guess I'll go then", imageUrl = "file:///android_asset/memes/spongebob_ight_imma_head_out.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "guess_ill_cry", name = "Guess I'll cry", imageUrl = "file:///android_asset/memes/crying_on_floor.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "cat_in_a_box", name = "Cat in a Box", imageUrl = "file:///android_asset/memes/oh_no_black_cat.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "suspicious_dog", name = "Suspicious Dog", imageUrl = "file:///android_asset/memes/skeptical_snake.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "me_after_2_hours_of_sleep", name = "Me after 2 hours of sleep", imageUrl = "file:///android_asset/memes/hide_the_pain_harold.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "understanding_the_joke_5_minutes_later", name = "Understanding the joke 5 minutes later", imageUrl = "file:///android_asset/memes/laughing_leo.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "teacher_says_surprise_test", name = "Teacher says surprise test", imageUrl = "file:///android_asset/memes/surprised_pikachu.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "well_that_explains_everything", name = "Well that explains everything", imageUrl = "file:///android_asset/memes/roll_safe.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "when_the_internet_goes_down", name = "When the internet goes down", imageUrl = "file:///android_asset/memes/and_it_s_gone.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "when_salary_arrives", name = "When salary arrives", imageUrl = "file:///android_asset/memes/shut_up_and_take_my_money.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "when_salary_is_already_gone", name = "When salary is already gone", imageUrl = "file:///android_asset/memes/sad_pablo_escobar.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "ill_sleep_early_vs_me_at_3_am", name = "I'll sleep early vs Me at 3 AM", imageUrl = "file:///android_asset/memes/daily_struggle.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "friend_says_i_have_an_idea", name = "Friend says I have an idea", imageUrl = "file:///android_asset/memes/charlie_conspiracy_always_sunny_in_phili.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "after_eating_too_much_shawarma", name = "After eating too much shawarma", imageUrl = "file:///android_asset/memes/i_immediately_regret_this_decision.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "laughing_at_your_own_joke", name = "Laughing at your own joke", imageUrl = "file:///android_asset/memes/laughing_lizard.jpg", rarity = MemeRarity.COMMON, power = 10),
        Meme(id = "realizing_tomorrow_is_monday", name = "Realizing tomorrow is Monday", imageUrl = "file:///android_asset/memes/minor_mistake_marvin.jpg", rarity = MemeRarity.COMMON, power = 17),
        Meme(id = "fine_whatever", name = "Fine whatever", imageUrl = "file:///android_asset/memes/this_is_fine.jpg", rarity = MemeRarity.COMMON, power = 24),
        Meme(id = "natasha_we_dropped_everything", name = "Natasha We Dropped Everything", imageUrl = "file:///android_asset/memes/drake_hotline_bling.jpg", rarity = MemeRarity.UNCOMMON, power = 27),
        Meme(id = "this_is_fine_dog", name = "This Is Fine Dog", imageUrl = "file:///android_asset/memes/this_is_fine.jpg", rarity = MemeRarity.UNCOMMON, power = 34),
        Meme(id = "think_mark", name = "Think Mark", imageUrl = "file:///android_asset/memes/matrix_morpheus.jpg", rarity = MemeRarity.UNCOMMON, power = 41),
        Meme(id = "i_have_paws_cant_do_it", name = "I have paws (can't do it)", imageUrl = "file:///android_asset/memes/no_take_only_throw.jpg", rarity = MemeRarity.UNCOMMON, power = 48),
        Meme(id = "sitting_in_the_corner_thinking", name = "Sitting in the corner thinking", imageUrl = "file:///android_asset/memes/squidward_window.jpg", rarity = MemeRarity.UNCOMMON, power = 29),
        Meme(id = "distracted_boyfriend", name = "Distracted Boyfriend", imageUrl = "file:///android_asset/memes/distracted_boyfriend.jpg", rarity = MemeRarity.UNCOMMON, power = 36),
        Meme(id = "crying_cat", name = "Crying Cat", imageUrl = "file:///android_asset/memes/crying_on_floor.jpg", rarity = MemeRarity.UNCOMMON, power = 43),
        Meme(id = "salad_cat", name = "Salad Cat", imageUrl = "file:///android_asset/memes/woman_yelling_at_a_cat.jpg", rarity = MemeRarity.UNCOMMON, power = 50),
        Meme(id = "im_tired_im_leaving", name = "I'm tired I'm leaving", imageUrl = "file:///android_asset/memes/spongebob_ight_imma_head_out.jpg", rarity = MemeRarity.UNCOMMON, power = 31),
        Meme(id = "realizing_it_was_a_joke", name = "Realizing it was a joke", imageUrl = "file:///android_asset/memes/scooby_doo_mask_reveal.jpg", rarity = MemeRarity.UNCOMMON, power = 38),
        Meme(id = "everything_according_to_plan", name = "Everything according to plan", imageUrl = "file:///android_asset/memes/gru_s_plan.jpg", rarity = MemeRarity.UNCOMMON, power = 45),
        Meme(id = "wrong_door_moment", name = "Wrong door moment", imageUrl = "file:///android_asset/memes/left_exit_12_off_ramp.jpg", rarity = MemeRarity.UNCOMMON, power = 26),
        Meme(id = "judging_cat", name = "Judging Cat", imageUrl = "file:///android_asset/memes/kombucha_girl.jpg", rarity = MemeRarity.UNCOMMON, power = 33),
        Meme(id = "thats_the_base", name = "That's the base", imageUrl = "file:///android_asset/memes/change_my_mind.jpg", rarity = MemeRarity.UNCOMMON, power = 40),
        Meme(id = "ill_do_it_tomorrow", name = "I'll do it tomorrow", imageUrl = "file:///android_asset/memes/panik_kalm_panik.jpg", rarity = MemeRarity.UNCOMMON, power = 47),
        Meme(id = "just_one_more_episode", name = "Just one more episode", imageUrl = "file:///android_asset/memes/but_it_s_honest_work.jpg", rarity = MemeRarity.UNCOMMON, power = 28),
        Meme(id = "when_they_say_its_free", name = "When they say it's free", imageUrl = "file:///android_asset/memes/it_s_happening.jpg", rarity = MemeRarity.UNCOMMON, power = 35),
        Meme(id = "meh", name = "Meh", imageUrl = "file:///android_asset/memes/grumpy_cat.jpg", rarity = MemeRarity.UNCOMMON, power = 42),
        Meme(id = "cat_raising_paw", name = "Cat Raising Paw", imageUrl = "file:///android_asset/memes/oh_no_black_cat.jpg", rarity = MemeRarity.UNCOMMON, power = 49),
        Meme(id = "start_of_diet_vs_evening", name = "Start of diet vs evening", imageUrl = "file:///android_asset/memes/two_buttons.jpg", rarity = MemeRarity.UNCOMMON, power = 30),
        Meme(id = "zhdun", name = "Zhdun", imageUrl = "file:///android_asset/memes/waiting_skeleton.jpg", rarity = MemeRarity.RARE, power = 57),
        Meme(id = "hide_the_pain_harold", name = "Hide the Pain Harold", imageUrl = "file:///android_asset/memes/hide_the_pain_harold.jpg", rarity = MemeRarity.RARE, power = 64),
        Meme(id = "cheems", name = "Cheems", imageUrl = "file:///android_asset/memes/cheems.jpg", rarity = MemeRarity.RARE, power = 45),
        Meme(id = "go_on_tell_me_more", name = "Go on tell me more", imageUrl = "file:///android_asset/memes/futurama_fry.jpg", rarity = MemeRarity.RARE, power = 52),
        Meme(id = "i_dont_understand_but_i_approve", name = "I don't understand but I approve", imageUrl = "file:///android_asset/memes/confused_gandalf.jpg", rarity = MemeRarity.RARE, power = 59),
        Meme(id = "sigma_face", name = "Sigma Face", imageUrl = "file:///android_asset/memes/laughing_leo.jpg", rarity = MemeRarity.RARE, power = 66),
        Meme(id = "npc_face", name = "NPC Face", imageUrl = "file:///android_asset/memes/trollface.jpg", rarity = MemeRarity.RARE, power = 47),
        Meme(id = "that_was_too_much", name = "That was too much", imageUrl = "file:///android_asset/memes/i_immediately_regret_this_decision.jpg", rarity = MemeRarity.RARE, power = 54),
        Meme(id = "saying_something_stupid_in_class", name = "Saying something stupid in class", imageUrl = "file:///android_asset/memes/awkward_moment_seal.jpg", rarity = MemeRarity.RARE, power = 61),
        Meme(id = "galaxy_brain", name = "Galaxy Brain", imageUrl = "file:///android_asset/memes/galaxy_brain.jpg", rarity = MemeRarity.RARE, power = 68),
        Meme(id = "chef_asking_if_everything_was_good", name = "Chef asking if everything was good", imageUrl = "file:///android_asset/memes/salt_bae.jpg", rarity = MemeRarity.EPIC, power = 65),
        Meme(id = "me_seeing_90_percent_discount", name = "Me seeing 90 percent discount", imageUrl = "file:///android_asset/memes/shut_up_and_take_my_money.jpg", rarity = MemeRarity.EPIC, power = 72),
        Meme(id = "dead_inside", name = "Dead Inside", imageUrl = "file:///android_asset/memes/facepalm.jpg", rarity = MemeRarity.EPIC, power = 79),
        Meme(id = "megamind_brain_plan", name = "Megamind Brain Plan", imageUrl = "file:///android_asset/memes/megamind_peeking.jpg", rarity = MemeRarity.EPIC, power = 65),
        Meme(id = "me_and_my_last_nerve", name = "Me and my last nerve", imageUrl = "file:///android_asset/memes/expanding_brain.jpg", rarity = MemeRarity.EPIC, power = 72),
        Meme(id = "when_the_meme_goes_too_far", name = "When the meme goes too far", imageUrl = "file:///android_asset/memes/elmo_choosing_cocaine.jpg", rarity = MemeRarity.EPIC, power = 79),
        Meme(id = "that_exact_moment", name = "That exact moment", imageUrl = "file:///android_asset/memes/spider_man_pointing_at_spider_man.jpg", rarity = MemeRarity.EPIC, power = 65),
        Meme(id = "gigachad", name = "Gigachad", imageUrl = "file:///android_asset/memes/laughing_leo.jpg", rarity = MemeRarity.LEGENDARY, power = 97),
        Meme(id = "golden_doge", name = "Golden Doge", imageUrl = "file:///android_asset/memes/doge.jpg", rarity = MemeRarity.LEGENDARY, power = 93),
        Meme(id = "legendary_zhdun", name = "Legendary Zhdun", imageUrl = "file:///android_asset/memes/waiting_skeleton.jpg", rarity = MemeRarity.LEGENDARY, power = 100),
    )

    fun dealHand(count: Int = 5): List<Meme> = allMemes.shuffled().take(count)

    fun getAll(): List<Meme> = allMemes
}
