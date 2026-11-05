# Gói dữ liệu từ vựng chưa đưa vào app

`vocab-extra.json`: 42 nhóm / 122 mục đã soạn xong nhưng chưa nạp vào app (để dành cho bản cập nhật sau).
Cấu trúc mỗi nhóm: `gid, kind, adult, rel, tip, e[]`; mỗi mục có `w, pos, us, uk, vi, reg, nu (sắc thái), ex, exvi, oth (nghĩa khác), src, conf`.
Nguồn và giấy phép: xem `../NOTICE.md`. Trường `exsrc`/`src` cho biết ví dụ lấy từ WordNet hay tự biên soạn.
Ghi chú: các mục này chưa được người bản ngữ rà soát; các mục `adult: true` chỉ dành cho người từ 18 tuổi.
