package hh.project.discgolf.enums

import hh.project.discgolf.repositories.UserRepository

// Can be found from https://discgolfunited.com/blog/post/dictionary-rules-and-scoring
// https://www.baeldung.com/kotlin/enum
enum class ScoringSystem {
    ACE {
        override fun getResults(userId: Long, userRepository: UserRepository) = userRepository.getAces(userId)
    },
    ALBATROSS {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(-3, userId)
    },
    EAGLE {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(-2, userId)
    },
    BIRDIE {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(-1, userId)
    },
    PAR {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(0, userId)
    },
    BOGEY {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(1, userId)
    },
    DOUBLE_BOGEY {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(2, userId)
    },
    TRIPLE_BOGEY {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getResults(3, userId)
    },
    OVER_TRIPLE_BOGEY {
        override fun getResults(userId: Long, userRepository: UserRepository): Int = userRepository.getOverTripleBogeys(userId)
    };

    abstract fun getResults(userId : Long, userRepository: UserRepository) : Int
}