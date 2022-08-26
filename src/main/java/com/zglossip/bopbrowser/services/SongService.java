package com.zglossip.bopbrowser.services;

import com.zglossip.bopbrowser.domains.categories.FullSong;
import com.zglossip.bopbrowser.domains.categories.Song;

public interface SongService extends AbstractService<Song> {

  FullSong getFullSong(final int id);
}
