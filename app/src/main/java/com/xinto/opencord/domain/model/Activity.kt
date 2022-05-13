package com.xinto.opencord.domain.model

import com.xinto.enumgetter.GetterGen

@GetterGen
enum class ActivityType(val value: Int) {
    Game(0),
    Streaming(1),
    Listening(2),
    Watching(3),
    Custom(4),
    Competing(5),
    Unknown(-1);

    companion object
}

interface DomainActivity {
    val name: String
    val createdAt: Long
    val type: ActivityType
//    val timestamps: DomainActivityTimestamp?
//    val applicationId: ULong?
//    val details: String?
//    val state: String?
//    val emoji: DomainActivityEmoji?
//    val party: DomainActivityParty?
//    val assets: DomainActivityAssets?
//    val secrets: DomainActivitySecrets?
//    val instance: Boolean?
//    val buttons: List<DomainActivityButton>?
//    val id: String?
    // TODO: https://discord.com/developers/docs/topics/gateway#activity-object-activity-flags
//    val flags: Int?
}

data class DomainActivityGame(
    override val name: String,
    override val createdAt: Long,
    val id: String,
    val state: String,
    val details: String,
    val applicationId: ULong,
    val party: DomainActivityParty?,
    val assets: DomainActivityAssets?,
    val secrets: DomainActivitySecrets?,
    val timestamps: DomainActivityTimestamp?,
) : DomainActivity {
    override val type = ActivityType.Game
}

data class DomainActivityStreaming(
    override val name: String,
    override val createdAt: Long,
    val id: String,
    val url: String,
    val state: String,
    val details: String,
    val assets: DomainActivityAssets,
) : DomainActivity {
    override val type = ActivityType.Streaming
}

data class DomainActivityCustom(
    override val name: String,
    override val createdAt: Long,
    val state: String,
    val emoji: DomainActivityEmoji?,
) : DomainActivity {
    override val type = ActivityType.Custom
}

// TODO: remove this once all activity types are implemented
data class DomainActivityUnknown(
    override val name: String,
    override val createdAt: Long,
) : DomainActivity {
    override val type = ActivityType.Unknown
}

// TODO: use a partial emoji instead
data class DomainActivityEmoji(
    val name: String?,
    val id: ULong?,
    val animated: Boolean?,
)

// TODO: instant serializers
data class DomainActivityTimestamp(
    val start: Int?,
    val end: Int?,
)

data class DomainActivityParty(
    val id: String?,
    val currentSize: Int?,
    val maxSize: Int?,
)

data class DomainActivityAssets(
    val largeImage: String?,
    val largeText: String?,
    val smallImage: String?,
    val smallText: String?,
)

data class DomainActivitySecrets(
    val join: String?,
    val spectate: String?,
    val match: String?,
)

data class DomainActivityButton(
    val label: String,
    val url: String,
)