package Model.Shared;

import java.io.Serializable;

public enum RequestType implements Serializable {
    HighScore,
    Opportunity,
    Sets,
    SetHighScore,
    CreateLobby,
    GetLobby,
    StartGame,
    JoinLobby,
    GameUpdate,
    SendLobby,
    ServergameStart,
    LeaveLobby,
    RemoveLobby,
    LobbyJoined,
    Oppertunity,
    OppertunityActive
}
