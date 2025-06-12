# ファイルを読み込んで大文字に変換し、書き戻す
with open("words.txt", "r", encoding="utf-8") as file:
    content = file.read()

content_upper = content.upper()

with open("words.txt", "w", encoding="utf-8") as file:
    file.write(content_upper)