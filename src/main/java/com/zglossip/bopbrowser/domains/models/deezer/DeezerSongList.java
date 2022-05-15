package com.zglossip.bopbrowser.domains.models.deezer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerSongList extends AbstractDeezerList<DeezerSong> {
}
